package app.medeco.domain.use_case.pharmacies

import app.medeco.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class GetDonationRequestUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(id: String) = repository.getDonationRequest(id)
}