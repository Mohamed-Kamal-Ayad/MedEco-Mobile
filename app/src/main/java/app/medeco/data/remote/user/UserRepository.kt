package app.medeco.data.remote.user

import app.medeco.data.remote.user.model.CreateOrderBody
import org.koin.core.annotation.Single

@Single
class UserRepository(
    private val userApi: UserApi,
) {
    suspend fun getAllPharmacies() = userApi.getAllPharmacies()

    suspend fun makeDonateOrder(data: CreateOrderBody) = userApi.makeOrder(data)

    suspend fun getAllDrugs() = userApi.getAllDrugs()

    suspend fun getPoints() = userApi.getUserPoints()
}