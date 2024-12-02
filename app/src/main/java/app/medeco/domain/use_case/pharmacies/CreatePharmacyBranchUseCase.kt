package app.medeco.domain.use_case.pharmacies

import app.medeco.data.remote.pharmacy.PharmacyRepository
import app.medeco.data.remote.pharmacy.model.CreatePharmacyBranchBody
import org.koin.core.annotation.Single

@Single
class CreatePharmacyBranchUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(data: CreatePharmacyBranchBody) = repository.createBranch(data)
}