package app.medeco.domain.model.notification

data class Notification(
    val id: String,
    val data: String,
    val createdAt: Long,
    val updatedAt: Long,
    val readAt: Long? = null
)
