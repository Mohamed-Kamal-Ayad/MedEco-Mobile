package app.medeco.presentation.pharmacy.branch

import app.medeco.domain.model.pharmacy.PharmacyBranch

data class ManageBranchesUiState(
    val loading: Boolean = false,
    val branches: List<PharmacyBranch> = emptyList(),
    val error: String? = null
)
