package app.medeco.domain.use_case.assistant

import app.medeco.domain.model.assistant.AiMessage
import app.medeco.domain.model.assistant.NetworkResult
import app.medeco.domain.repository.assistant.AssistantApi
import org.koin.core.annotation.Single
import java.io.IOException

@Single
class SendAiMessageUseCase(
    private val api: AssistantApi
) {
    suspend operator fun invoke(
        messages: List<AiMessage>
    ): NetworkResult<AiMessage> {
        return try {
            api.sendMessage(messages)
        } catch (e: IOException) {
            e.printStackTrace()
            NetworkResult.InternetError
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.OtherError()
        }
    }
}