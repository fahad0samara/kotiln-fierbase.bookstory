package com.fahad.list_food.ui.screen.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.fahad.auth_firebase.domain.model.Response
import com.fahad.auth_firebase.domain.model.User
import com.fahad.auth_firebase.domain.repository.AuthRepository
import com.fahad.list_food.ui.navigation.Graph
import com.fahad.list_food.ui.screen.UserDataViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository,
                                            private val userDataViewModel: UserDataViewModel
    ) : ViewModel() {
    private val _loginState = MutableStateFlow<Response<User>>(Response.Loading)
    val loginState: StateFlow<Response<User>> = _loginState
    private val _isLoading = mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading.value
    fun login(email: String, password: String, navController: NavController) {
        _isLoading.value = true  // Set loading state to true at the beginning of the login process
        viewModelScope.launch {
            _loginState.value = Response.Loading
            val loginResult = repository.loginUser(email, password).first()
            _loginState.value = loginResult
            _isLoading.value = false


            if (loginResult is Response.Success) {
                // Login successful, update the user in the ViewModel
                val user = loginResult.data

                userDataViewModel.setUser(user)
              navController.navigate( Graph.HOME)
            }


        }
    }
}
