package app.medeco.data.remote.assistant

import app.medeco.data.remote.assistant.AssistantConstants.CHATBOT_BASE_URL
import app.medeco.data.remote.assistant.model.AssistantApiResponse
import app.medeco.data.remote.assistant.model.AssistantQueryBody
import app.medeco.data.remote.assistant.model.AssistantQueryResponse
import app.medeco.data.remote.assistant.model.toAssistantRequestBody
import app.medeco.domain.model.assistant.AiMessage
import app.medeco.domain.model.assistant.AiMessageType
import app.medeco.domain.model.assistant.NetworkResult
import app.medeco.domain.repository.assistant.AssistantApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
@Single
class AssistantApiImpl(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) : AssistantApi {

    override suspend fun sendQuery(query: String) = withContext(ioDispatcher) {
        try {
            val response = client.post(CHATBOT_BASE_URL) {
                setBody(AssistantQueryBody(query = query))
            }.body<AssistantQueryResponse>()
            NetworkResult.Success(response.response)
        } catch (_: Exception) {
            NetworkResult.OtherError()
        }
    }


    override suspend fun sendMessage(
        messages: List<AiMessage>
    ): NetworkResult<AiMessage> {
        return withContext(ioDispatcher) {
            val result = client.post {
                url {
                    appendPathSegments("api", "chat")
                }
                setBody(messages.toAssistantRequestBody())
            }.body<AssistantApiResponse>()
            NetworkResult.Success(
                AiMessage(
                    content = result.text,
                    type = AiMessageType.MODEL
                )
            )
        }
    }

}