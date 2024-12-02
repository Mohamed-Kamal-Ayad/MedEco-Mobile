package app.medeco.domain.repository.assistant

import app.medeco.domain.model.assistant.AiMessage
import app.medeco.domain.model.assistant.NetworkResult

interface AssistantApi {

    suspend fun sendQuery(query: String): NetworkResult<String>

    suspend fun sendMessage(messages: List<AiMessage>): NetworkResult<AiMessage>
}