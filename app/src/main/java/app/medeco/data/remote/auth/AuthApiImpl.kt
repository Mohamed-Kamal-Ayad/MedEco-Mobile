package app.medeco.data.remote.auth

import app.medeco.data.remote.NetworkConstants.API_BASE_URL
import app.medeco.data.remote.auth.model.LoginResponse
import app.medeco.data.remote.auth.model.SignUpResponse
import app.medeco.data.remote.auth.model.toLoginRequestBody
import app.medeco.domain.model.auth.LoginData
import app.medeco.domain.model.auth.SignUpData
import app.medeco.domain.repository.auth.AuthApi
import app.medeco.presentation.user.donate.FileManager
import app.medeco.util.formattedForNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class AuthApiImpl(
    private val client: HttpClient,
    private val fileManager: FileManager,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) : AuthApi {

    override suspend fun login(body: LoginData): LoginResponse {
        return withContext(ioDispatcher) {
            client.post(API_BASE_URL) {
                url {
                    appendPathSegments("login")
                }
                contentType(ContentType.Application.Json)
                setBody(body.toLoginRequestBody())
            }.body<LoginResponse>()
        }
    }

    override suspend fun signUp(
        body: SignUpData,
    ): SignUpResponse {
        return withContext(ioDispatcher) {
            val logoFileInfo = body.pharmacyLogo?.let { fileManager.uriToByteArray(it) }
            client.submitFormWithBinaryData(
                API_BASE_URL,
                formData = formData {
                    append("name", body.fullName)
                    append("email", body.email)
                    append("password", body.password)
                    append("password_confirmation", body.passwordConf)
                    append("phone", body.phone)
                    append("gender", body.gender.networkValue)
                    append("user_type", body.type.networkValue)
                    append("birthdate", body.birthDate.formattedForNetwork())
                    append("national_id", body.nationalId)
                    append("type", body.type.networkValue)
                    body.pharmacyName?.let {
                        append("pharmacy[is_accept_expired]", 1)
                        append("pharmacy[name]", it)
                    }
                    body.pharmacyHotline?.let {
                        append("pharmacy[hotline]", it)
                    }
                    logoFileInfo?.let { info ->
                        append(
                            "pharmacy[logo]",
                            info.bytes,
                            Headers.build {
                                append(HttpHeaders.ContentType, info.mimeType)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "filename=${info.name}"
                                )
                            })
                    }

                }
            ) {
                url {
                    appendPathSegments("register")
                }
                contentType(ContentType.Application.Json)

            }.body<SignUpResponse>()
        }
    }

}