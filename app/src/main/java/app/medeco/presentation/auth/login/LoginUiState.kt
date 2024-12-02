package app.medeco.presentation.auth.login

import app.medeco.util.UserType

data class LoginUiState(
    val done: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,
    val userType: UserType = UserType.USER
)