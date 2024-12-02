package app.medeco.presentation.auth.login

import app.medeco.domain.model.auth.LoginData
import app.medeco.presentation.common.Screen

sealed class LoginScreenEvent {
    data class Login(val loginData: LoginData) : LoginScreenEvent()
    data object NavigateUp : LoginScreenEvent()
    data object ForgotPassword : LoginScreenEvent()
    data class Navigate(val screen: Screen, val popUp: Boolean = false) : LoginScreenEvent()
}
