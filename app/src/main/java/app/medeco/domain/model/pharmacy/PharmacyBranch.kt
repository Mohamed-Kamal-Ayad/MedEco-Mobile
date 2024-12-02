package app.medeco.domain.model.pharmacy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyBranch(
    @SerialName("id") val id: Int,
    @SerialName("lat") val lat: Double? = null,
    @SerialName("lng") val lng: Double? = null,
    @SerialName("phone") val phone: String = "",
    @SerialName("address") val address: String = "",
    @SerialName("pharmacy") val pharmacy: Pharmacy? = null,
)
