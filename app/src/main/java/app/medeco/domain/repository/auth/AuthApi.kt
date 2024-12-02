package app.medeco.domain.repository.auth

import app.medeco.data.remote.auth.model.LoginResponse
import app.medeco.data.remote.auth.model.SignUpResponse
import app.medeco.domain.model.auth.LoginData
import app.medeco.domain.model.auth.SignUpData

interface AuthApi {

    suspend fun login(body: LoginData): LoginResponse

    suspend fun signUp(body: SignUpData): SignUpResponse
}