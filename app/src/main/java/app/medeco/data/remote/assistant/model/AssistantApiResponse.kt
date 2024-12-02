package app.medeco.data.remote.assistant.model

import app.medeco.data.remote.assistant.AssistantConstants.MESSAGE_MODEL_TYPE
import app.medeco.data.remote.assistant.AssistantConstants.MESSAGE_USER_TYPE
import app.medeco.domain.model.assistant.AiMessageType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantApiResponse(
    @SerialName("text")
    val text: String
)

val AiMessageType.apiRole
    get() = if (this == AiMessageType.USER) MESSAGE_USER_TYPE else MESSAGE_MODEL_TYPE