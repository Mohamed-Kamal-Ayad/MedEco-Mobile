package app.medeco.domain.use_case.pharmacies

import app.medeco.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class GetAllNetworkRequestsUseCase(
    private val pharmacyRepository: PharmacyRepository
) {
    suspend operator fun invoke() = pharmacyRepository.getAllNetworkRequests()
}