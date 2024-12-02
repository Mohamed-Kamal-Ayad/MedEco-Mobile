package app.medeco.domain.use_case.assistant

import app.medeco.domain.repository.assistant.AssistantApi
import org.koin.core.annotation.Single

@Single
class SendAssistantQueryUseCase(
    private val api: AssistantApi
) {
    suspend operator fun invoke(query: String) = api.sendQuery(query)
}