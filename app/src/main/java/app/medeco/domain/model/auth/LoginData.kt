package app.medeco.domain.model.auth

import app.medeco.util.UserType

data class LoginData(
    val email: String,
    val password: String,
    val type: UserType = UserType.USER
)
