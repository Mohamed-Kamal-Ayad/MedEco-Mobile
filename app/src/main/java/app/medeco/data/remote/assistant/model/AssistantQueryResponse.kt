package app.medeco.data.remote.assistant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantQueryResponse(
    @SerialName("response") val response: String
)