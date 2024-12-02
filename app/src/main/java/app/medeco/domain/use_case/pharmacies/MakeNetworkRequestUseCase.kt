package app.medeco.domain.use_case.pharmacies

import app.medeco.data.remote.pharmacy.model.NetworkRequest
import app.medeco.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class MakeNetworkRequestUseCase(
    private val pharmacyRepository: PharmacyRepository
) {
    suspend operator fun invoke(request: NetworkRequest) = pharmacyRepository.makeNetworkRequest(request)
}