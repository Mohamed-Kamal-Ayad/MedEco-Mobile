package app.medeco.domain.use_case.pharmacies

import app.medeco.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class ApproveDonationUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(id: String) = repository.approveDonateOrder(id)
}