package app.medeco.data.remote.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPoints(
    @SerialName("points") val points: Int
)