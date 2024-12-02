package app.medeco.presentation.pharmacy.network

import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto

sealed class PharmacyNetworkEvent {
    data class MakeRequest(val description: String, val branchId: Int): PharmacyNetworkEvent()
//    data class ApproveRequest(val request: GetNetworkRequestsDto): PharmacyNetworkEvent()
    data object NavigateUp: PharmacyNetworkEvent()
}