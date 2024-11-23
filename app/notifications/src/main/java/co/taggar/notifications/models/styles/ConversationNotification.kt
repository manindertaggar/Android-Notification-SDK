package co.taggar.notifications.models.styles

import androidx.core.app.NotificationCompat
import co.taggar.notifications.models.NotificationButton

class ConversationNotification(
    val conversation: List<NotificationCompat.MessagingStyle.Message>,
    id: Int,
    color: Int,
    buttons: List<NotificationButton>? = null,
    deeplink: String? = null,
) : Notification(
    id = id,
    color = color,
    buttons = buttons,
    deeplink = deeplink,
    title = "",
    message = ""
)