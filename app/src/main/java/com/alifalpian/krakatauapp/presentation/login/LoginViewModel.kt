package com.alifalpian.krakatauapp.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var loginUiState = MutableStateFlow(LoginUiState())
        private set

    val loginButtonEnabled get() = loginUiState.map {
        it.email.isNotEmpty() && it.password.length >= 6
    }

    fun onEmailChange(email: String) {
        loginUiState.value = loginUiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        loginUiState.value = loginUiState.value.copy(password = password)
    }

}