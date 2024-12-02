package app.medeco.data.remote.pharmacy.model

import app.medeco.domain.model.pharmacy.Pharmacy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllPharmaciesDto(
    @SerialName("data") val pharmacies: List<Pharmacy>
)