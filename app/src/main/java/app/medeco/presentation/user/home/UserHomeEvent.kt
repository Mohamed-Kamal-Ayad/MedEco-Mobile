package app.medeco.presentation.user.home

sealed class UserHomeEvent {
    data object Refresh : UserHomeEvent()
}