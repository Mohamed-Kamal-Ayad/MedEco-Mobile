package app.medeco.data.remote.pharmacy.model

import app.medeco.domain.model.pharmacy.Pharmacy
import app.medeco.domain.model.pharmacy.PharmacyBranch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNetworkRequestsDto(
    @SerialName("id") val id: Int,
    @SerialName("description") val description: String,
    @SerialName("sender") val sender: NetworkRequestSender
)

@Serializable
data class NetworkRequestSender(
    @SerialName("pharmacy_branch") val branch: PharmacyBranch,
    @SerialName("pharmacy") val pharmacy: Pharmacy
)
