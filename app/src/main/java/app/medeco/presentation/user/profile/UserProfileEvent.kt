package app.medeco.presentation.user.profile

import app.medeco.presentation.common.Screen

sealed class UserProfileEvent {
    data object SignOut : UserProfileEvent()
    data class Navigate(val screen: Screen, val popUp: Boolean = false) : UserProfileEvent()
}
