package app.medeco.presentation.notifications

import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto
import app.medeco.presentation.pharmacy.network.PharmacyNetworkEvent

sealed class NotificationsScreenEvent {
    data class ApproveRequest(val request: GetNetworkRequestsDto): NotificationsScreenEvent()

}