package app.medeco.data.remote.user

import app.medeco.data.remote.pharmacy.model.GetAllPharmaciesDto
import app.medeco.data.remote.user.model.CreateOrderBody
import app.medeco.data.remote.user.model.CreateOrderResponse
import app.medeco.data.remote.user.model.GetAllDrugsDto
import app.medeco.data.remote.user.model.UserPoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class UserApi(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) {

    suspend fun getAllPharmacies() = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("pharmacy", "pharmacies")
            }
        }.body<GetAllPharmaciesDto>().pharmacies
    }

    suspend fun getAllDrugs() = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("user", "drugs")
            }
        }.body<GetAllDrugsDto>().data
    }

    suspend fun makeOrder(body: CreateOrderBody) = withContext(ioDispatcher) {
        client.post {
            url {
                appendPathSegments("user", "orders")
            }
            setBody(body)
        }.body<CreateOrderResponse>()
    }

    suspend fun getUserPoints() = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("user", "points")
            }
        }.body<UserPoints>()
    }

}