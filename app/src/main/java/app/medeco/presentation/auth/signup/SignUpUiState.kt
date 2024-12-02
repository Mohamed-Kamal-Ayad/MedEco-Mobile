package app.medeco.presentation.auth.signup

import app.medeco.util.UserType

data class SignUpUiState(
    val done: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,
    val userType: UserType = UserType.USER
)