package co.taggar.notifications.models.styles

import co.taggar.notifications.models.NotificationButton

class BigTextNotification(
    id: Int,
    title: String,
    message: String,
    color: Int,
    buttons: List<NotificationButton>? = null,
    deeplink: String? = null,
) : Notification(
    id = id,
    title = title,
    message = message,
    color = color,
    buttons = buttons,
    deeplink = deeplink
)