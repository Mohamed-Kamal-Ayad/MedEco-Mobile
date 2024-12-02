package app.medeco.domain.use_case.user

import app.medeco.data.remote.user.UserRepository
import org.koin.core.annotation.Single

@Single
class GetUserPointsUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getPoints().points
}
