package app.medeco.presentation.user.donate.donation_data

import androidx.compose.runtime.Immutable
import app.medeco.domain.model.drug.Drug

@Immutable
data class DonationUiState(
    val drugs: List<Drug> = emptyList(),
    val orderId: String? = null,
    val loading: Boolean = false,
    val error: String? = null
)
