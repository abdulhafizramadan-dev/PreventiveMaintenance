package com.alifalpian.krakatauapp.presentation.login

import com.alifalpian.krakatauapp.util.emptyString

data class LoginUiState(
    val email: String = emptyString(),
    val password: String = emptyString()
)
