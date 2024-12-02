package app.medeco.data.remote.auth.model

import app.medeco.data.remote.pharmacy.model.PharmacyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("token") val token: String,
    @SerialName("user") val user: UserDto? = null,
    @SerialName("pharmacy") val pharmacy: PharmacyDto? = null
)
