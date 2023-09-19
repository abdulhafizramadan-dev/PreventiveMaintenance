package com.alifalpian.krakatauapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alifalpian.krakatauapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

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

    fun onLoginLoadingState(loadingState: Boolean) {
        loginUiState.value = loginUiState.value.copy(loadingState = loadingState)
    }


    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.signInWithEmailAndPassword(email, password).collect { result ->
                loginUiState.value = loginUiState.value.copy(
                    loginResult = result
                )
            }
        }
    }

    fun getUser(uid: String) {
        viewModelScope.launch {
            loginUseCase.getUser(uid).collect { result ->
                loginUiState.value = loginUiState.value.copy(
                    loggedUser = result
                )
            }
        }
    }

}