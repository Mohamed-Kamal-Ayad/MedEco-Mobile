package app.medeco.domain.use_case.auth

import app.medeco.domain.model.auth.SignUpData
import app.medeco.domain.repository.auth.AuthApi
import org.koin.core.annotation.Single

@Single
class SignUpUseCase(
    private val api: AuthApi
) {
    suspend operator fun invoke(signUpData: SignUpData) = api.signUp(signUpData)
}