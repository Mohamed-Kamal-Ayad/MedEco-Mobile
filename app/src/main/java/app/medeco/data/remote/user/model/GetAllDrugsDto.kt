package app.medeco.data.remote.user.model

import app.medeco.domain.model.drug.Drug
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllDrugsDto(
    @SerialName("data") val data: List<Drug>
)