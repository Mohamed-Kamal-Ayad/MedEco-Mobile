package app.medeco.data.remote.assistant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssistantQueryBody(
    @SerialName("query") val query: String
)