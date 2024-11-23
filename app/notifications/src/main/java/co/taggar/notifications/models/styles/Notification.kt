package co.taggar.notifications.models.styles

import co.taggar.notifications.models.NotificationButton

abstract class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val color: Int,
    val buttons: List<NotificationButton>? = null,
    val deeplink: String? = null,
)
