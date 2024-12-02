package app.medeco.presentation.notifications

import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto

sealed interface Notification

data class NetworkRequestNotification(val request: GetNetworkRequestsDto): Notification
data class BasicNotification(val content: String): Notification