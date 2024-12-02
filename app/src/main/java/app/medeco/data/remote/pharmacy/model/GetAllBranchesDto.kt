package app.medeco.data.remote.pharmacy.model

import app.medeco.domain.model.pharmacy.PharmacyBranch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllBranchesDto(
    @SerialName("data") val data: List<PharmacyBranch>
)