package app.medeco.presentation.assistant

import app.medeco.domain.model.assistant.AiMessage
import app.medeco.domain.model.assistant.NetworkResult

data class AssistantUiState(
    val messages: List<AiMessage> = emptyList(),
    val loading: Boolean = false,
    val error: NetworkResult.Failure? = null,
)