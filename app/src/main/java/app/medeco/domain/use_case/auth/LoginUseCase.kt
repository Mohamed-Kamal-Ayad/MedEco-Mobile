package app.medeco.domain.use_case.auth

import app.medeco.domain.model.auth.LoginData
import app.medeco.domain.repository.auth.AuthApi
import org.koin.core.annotation.Single

@Single
class LoginUseCase(
    private val api: AuthApi
) {
    suspend operator fun invoke(loginData: LoginData) = api.login(loginData)
}