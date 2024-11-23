package co.taggar.notifications.models.styles

import co.taggar.notifications.models.NotificationButton

class InboxNotification(
    val lines: List<String>,
    val summeryText: String? = null,
    val bigContentTitle: String? = null,
    message: String,
    id: Int,
    title: String,
    color: Int,
    buttons: List<NotificationButton>? = null,
    deeplink: String? = null,
) : Notification(
    id = id, title = title, message = message, color = color, buttons = buttons, deeplink = deeplink
)