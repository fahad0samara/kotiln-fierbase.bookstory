package com.fahad.list_food.data.repository

import android.net.Uri
import android.util.Log
import com.fahad.auth_firebase.domain.model.Response
import com.fahad.auth_firebase.domain.model.User
import com.fahad.auth_firebase.domain.repository.AuthRepository
import com.fahad.auth_firebase.util.valid.validateEmailAndPassword
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import kotlinx.coroutines.flow.single
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    private val auth = Firebase.auth
    private val storage = FirebaseStorage.getInstance()

    override suspend fun registerUser(
        email: String, password: String, displayName: String, photoUri: String
    ): Flow<Response<User>> = flow {
        val validationResponse = validateEmailAndPassword(email, password)
        if (validationResponse is Response.Failure) {
            emit(validationResponse)
            return@flow
        }
        try {
            // Register the user with email and password
            auth.createUserWithEmailAndPassword(email, password).await()

            // Upload the image to Firebase Storage
            val uploadedPhotoUrl = uploadImageToFirebaseStorage(Uri.parse(photoUri))
            // Send email verification


            // After successful registration, set the display name and photo URL
            val user = auth.currentUser
            val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(displayName)
                .setPhotoUri(Uri.parse(uploadedPhotoUrl)).build()
            user?.updateProfile(profileUpdates)?.await()
            user?.sendEmailVerification()?.await()
            // Fetch the latest user data from Firebase
            val updatedUser = getUserDataFromFirebase()
            emit(Response.Success(updatedUser))
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(Response.Failure(Exception("The email address is already in use by another account.")))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun loginUser(
        email: String, password: String,
    ): Flow<Response<User>> = flow {
        val validationResponse = validateEmailAndPassword(email, password)
        if (validationResponse is Response.Failure) {
            emit(validationResponse)
            return@flow
        }

        try {
            auth.signInWithEmailAndPassword(email, password).await()

            // Fetch the latest user data from Firebase
            val updatedUser = getUserDataFromFirebase()



            emit(Response.Success(updatedUser))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)


    private suspend fun uploadImageToFirebaseStorage(photoUri: Uri): String {
        return try {
            // Create a reference to the image file in Firebase Storage
            val storageRef = storage.reference
            val imageRef = storageRef.child("profile_images/${photoUri.lastPathSegment}")

            // Upload the image to Firebase Storage
            imageRef.putFile(photoUri).await()

            // Get the download URL
            val downloadUrl = imageRef.downloadUrl.await()

            downloadUrl.toString()
        } catch (e: Exception) {
            // Handle the exception, e.g., log it or throw a custom exception
            Log.e("AuthRepositoryImpl", "Error uploading image to Firebase Storage: ${e.message}")
            ""
        }
    }

    private fun getUserDataFromFirebase(): User {
        val firebaseUser = auth.currentUser
        val uid = firebaseUser?.uid ?: throw Exception("User is not logged in")

        val email = firebaseUser.email ?: ""
        val displayName = firebaseUser.displayName ?: ""
        val photoUrl = downloadImageFromFirebaseStorage(firebaseUser.photoUrl?.toString() ?: "")
        val isEmailVerified = firebaseUser.isEmailVerified

        return User(uid, email, displayName, photoUrl, isEmailVerified)
    }


    private fun downloadImageFromFirebaseStorage(photoUrl: String?): String {
        return try {
            // Ensure that the photoUrl is a full download URL
            if (photoUrl != null && photoUrl.startsWith("https://")) {
                // If it's a Firebase Storage URL, use it directly
                photoUrl

            } else {
                // Handle other cases or log an error
                Log.e("AuthRepositoryImpl", "Invalid photoUrl format: $photoUrl")
                ""
            }
        } catch (e: Exception) {
            // Handle the exception, e.g., log it or throw a custom exception
            Log.e("AuthRepositoryImpl", "Error processing photoUrl: $photoUrl. Error: ${e.message}")
            ""
        }
    }


    override suspend fun getUserData(): Response<User> = flow {
        try {
            val user = getUserDataFromFirebase()
            emit(Response.Success(user))
            Log.d("response", "getUserData: $user")
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO).single()

    // In AuthRepository

    override suspend fun updateUserProfile(
        uid: String, displayName: String, photoUri: String
    ): Response<User> = flow {
        try {
            if (displayName.isBlank()) {
                emit(Response.Failure(Exception("Display name cannot be empty")))
                return@flow
            }

            // Fetch the current user's data
            val currentUser = getUserDataFromFirebase()

            // Delete the previous image from Firebase Storage
            currentUser.photoUrl?.let { deleteImageFromFirebaseStorage(it) }

            // Upload the new image to Firebase Storage
            val uploadedPhotoUrl = uploadImageToFirebaseStorage(Uri.parse(photoUri))

            if (uploadedPhotoUrl.isEmpty()) {
                emit(Response.Failure(Exception("Failed to upload image to Firebase Storage")))
                return@flow
            }

            // Update the user's profile with the new image URL
            val user = auth.currentUser
            val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(displayName)
                .setPhotoUri(Uri.parse(uploadedPhotoUrl)).build()

            user?.updateProfile(profileUpdates)?.await()

            // Fetch the updated user data from Firebase
            val updatedUser = getUserDataFromFirebase()
            emit(Response.Success(updatedUser))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO).single()


    private suspend fun deleteImageFromFirebaseStorage(photoUrl: String): Response<Unit> {
        return try {
            if (photoUrl.isNotEmpty() && photoUrl.startsWith("https://")) {
                val storageRef = storage.getReferenceFromUrl(photoUrl)
                storageRef.delete().await()
                Response.Success(Unit)
            } else {
                Response.Failure(Exception("Invalid photoUrl format"))
            }
        } catch (e: Exception) {
            // Handle the exception, e.g., log it or throw a custom exception
            Response.Failure(e)
        }
    }

    override suspend fun sendEmailVerification(): Response<Unit> = flow {
        try {
            val user = auth.currentUser
            // If the user is already verified, don't send email again
            if (user?.isEmailVerified == true) {
                emit(Response.Failure(Exception("Your email is already verified")))
            } else {
                user?.sendEmailVerification()?.await()
                emit(Response.Success(Unit))
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO).single()



    override suspend fun markEmailAsVerified(): Response<Unit> = flow {
        try {
            val user = auth.currentUser
            user?.reload()?.await() // Reload the user data to get the latest email verification status
            val updatedUser = auth.currentUser // Retrieve the user again to get the updated status

            if (updatedUser?.isEmailVerified == true) {
                emit(Response.Success(Unit))
            } else {
                // If the user is not verified, send email again and show error message
                user?.sendEmailVerification()?.await()
                emit(Response.Failure(Exception("Your email is not verified. We have sent you another email. Please verify your email")))



            }
        } catch (e: Exception) {
            emit(Response.Failure(e))

        }
    }.flowOn(Dispatchers.IO).single()




    override suspend fun logout(): Response<Unit> = flow {
        try {
            auth.signOut()
            emit(Response.Success(Unit))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO).single()
}


















