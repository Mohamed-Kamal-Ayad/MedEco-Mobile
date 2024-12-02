package app.medeco.presentation.auth.signup

import app.medeco.domain.model.auth.SignUpData
import app.medeco.presentation.common.Screen

sealed class SignUpScreenEvent {
    data class SignUp(val signUpData: SignUpData) : SignUpScreenEvent()
    data object NavigateUp : SignUpScreenEvent()
    data class Navigate(val screen: Screen, val popUp: Boolean = false) : SignUpScreenEvent()
}
