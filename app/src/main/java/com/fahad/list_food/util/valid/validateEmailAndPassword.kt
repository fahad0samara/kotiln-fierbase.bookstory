package com.fahad.auth_firebase.util.valid

import com.fahad.auth_firebase.domain.model.Response


fun validateEmailAndPassword(email: String, password: String): Response<Unit> {
    if (email.isBlank()) {
        return Response.Failure(Exception("Email cannot be empty"))
    }

    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return Response.Failure(Exception("Invalid email format. Please enter a valid email address"))
    }

    if (password.isBlank()) {
        return Response.Failure(Exception("Password cannot be empty"))
    }

    if (password.length < 6) {
        return Response.Failure(Exception("Password should be at least 6 characters long"))
    }

    if (!password.any { it.isUpperCase() }) {
        return Response.Failure(Exception("Password should contain at least one uppercase letter"))
    }



    return Response.Success(Unit)
}

