package co.taggar.notifications.models

import android.widget.Button

class LargeNotification(
    val image: String,
    val message: String,
    id: Int,
    title: String,
    color: Int,
    buttons: List<Button> = listOf(),
    deeplink: String,
) : BaseNotification(
    id = id, title = title, color = color, buttons = buttons, type = NotificationType.Large,
    deeplink = deeplink
)