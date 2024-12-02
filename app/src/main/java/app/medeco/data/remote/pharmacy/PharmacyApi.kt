package app.medeco.data.remote.pharmacy

import app.medeco.data.remote.pharmacy.model.ApproveUserOrderBody
import app.medeco.data.remote.pharmacy.model.CreatePharmacyBranchBody
import app.medeco.data.remote.pharmacy.model.GetAllBranchesDto
import app.medeco.data.remote.pharmacy.model.GetAllPharmaciesDto
import app.medeco.data.remote.pharmacy.model.GetDonationDto
import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto
import app.medeco.data.remote.pharmacy.model.NetworkRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import io.ktor.http.isSuccess
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class PharmacyApi(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) {

    suspend fun makeNetworkRequest(body: NetworkRequest) =
        withContext(ioDispatcher) {
            client.post {
                url {
                    appendPathSegments("pharmacy", "requests")
                }
                setBody(body)
            }.also {
                if (!it.status.isSuccess()) throw Exception("Error while making request")
            }
        }

    suspend fun approveRequest(id: Int) =
        withContext(ioDispatcher) {
            client.put {
                url {
                    appendPathSegments("pharmacy", "network", "$id", "accept")
                }
            }.also {
                if (!it.status.isSuccess()) throw Exception("Error while approving request")
            }
        }

    suspend fun createPharmacyBranch(body: CreatePharmacyBranchBody) =
        withContext(ioDispatcher) {
            val success = client.post {
                url {
                    appendPathSegments("pharmacy", "pharmacy-branches")
                }
                setBody(body)
            }.status.isSuccess()
            if (!success) throw Exception("Create branch failed")
        }

    suspend fun approveDonateOrder(id: String) = withContext(ioDispatcher) {
        val response = client.post {
            url {
                appendPathSegments("pharmacy", "orders", id)
            }
            setBody(ApproveUserOrderBody())
        }
        if (!response.status.isSuccess()) throw Exception("Approve order failed")
    }

    suspend fun getAllNetworkRequests() = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("pharmacy", "network")
            }
        }.body<List<GetNetworkRequestsDto>>()
    }

    suspend fun getDonationRequest(id: String) = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("pharmacy", "orders", id)
            }
        }.body<GetDonationDto>()
    }

    suspend fun getAllBranches() = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("pharmacy", "branches")
                parameter("my_branches", "true")
            }
        }.body<GetAllBranchesDto>().data
    }

}