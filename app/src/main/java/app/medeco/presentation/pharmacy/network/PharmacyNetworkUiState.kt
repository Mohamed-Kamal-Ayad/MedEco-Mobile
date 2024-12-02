package app.medeco.presentation.pharmacy.network

import androidx.compose.runtime.Immutable
import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto
import app.medeco.domain.model.pharmacy.PharmacyBranch

@Immutable
data class PharmacyNetworkUiState(
    val loading: Boolean = false,
    val navigateUp: Boolean = false,
    val branches: List<PharmacyBranch> = emptyList(),
    val error: String? = null
)
