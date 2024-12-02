package app.medeco.domain.use_case.pharmacies

import app.medeco.data.remote.pharmacy.PharmacyRepository
import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto
import org.koin.core.annotation.Single

@Single
class ApproveNetworkRequestUseCase(
    private val pharmacyRepository: PharmacyRepository,
) {
    suspend operator fun invoke(request: GetNetworkRequestsDto) =
        pharmacyRepository.approveRequest(request.id)
}