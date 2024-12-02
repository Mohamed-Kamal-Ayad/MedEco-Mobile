package app.medeco.presentation.user.home

import androidx.compose.runtime.Stable

@Stable
data class UserHomeUiState(
    val userName: String = "",
    val hasNotifications: Boolean = false,
    val points: Int = 0,
    val error: String? = ""
)
