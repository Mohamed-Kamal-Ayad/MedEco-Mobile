package app.medeco.presentation.user.donate.donation_data

import app.medeco.domain.model.drug.Drug

sealed class DonationEvent {
    data class MakeOrder(val data: Map<Drug, Int>, val branchId: Int): DonationEvent()
    data object Done: DonationEvent()
}