package app.medeco.data.remote.pharmacy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkRequest(
    @SerialName("description") val description: String,
    @SerialName("pharmacy_branch_id") val branchId: Int
)
