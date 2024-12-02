package app.medeco.domain.use_case.user

import app.medeco.data.remote.user.UserRepository
import app.medeco.data.remote.user.model.CreateOrderBody
import org.koin.core.annotation.Single

@Single
class MakeDonateOrderUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(data: CreateOrderBody) = repository.makeDonateOrder(data)
}