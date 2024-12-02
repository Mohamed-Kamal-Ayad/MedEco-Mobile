package app.medeco.presentation.assistant

import app.medeco.domain.model.assistant.AiMessage

sealed interface AssistantEvent {
    data class SendMessage(val message: AiMessage): AssistantEvent
}
