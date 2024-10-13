package co.taggar.notifications.models

import android.widget.Button

open class BaseNotification(
    val id: Int,
    val title: String,
    val color: Int,
    val buttons: List<Button>,
    val deeplink: String,
    val type: NotificationType
) {

}

