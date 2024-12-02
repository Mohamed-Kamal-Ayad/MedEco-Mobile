package app.medeco.presentation.user.donate.approve

import app.medeco.data.remote.pharmacy.model.GetDonationData

data class ApproveDonationUiState(
    val approveLoading: Boolean = false,
    val loading: Boolean = false,
    val order: GetDonationData? = null,
    val success: Boolean = false,
    val error: String? = null
)
