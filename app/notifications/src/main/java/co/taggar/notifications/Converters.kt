package co.taggar.notifications

import android.graphics.Color
import android.graphics.Color.parseColor
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import co.taggar.notifications.Constants.DATA_CONVERSATION
import co.taggar.notifications.Constants.DATA_IMAGE
import co.taggar.notifications.Constants.DATA_LINES
import co.taggar.notifications.models.NotificationButton
import co.taggar.notifications.models.styles.BigTextNotification
import co.taggar.notifications.models.styles.ConversationNotification
import co.taggar.notifications.models.styles.DefaultNotification
import co.taggar.notifications.models.styles.InboxNotification
import co.taggar.notifications.models.styles.LargeNotification
import co.taggar.notifications.models.styles.Notification
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONArray


fun RemoteMessage.toNotification(): Notification? {
//  Validate message data
    if (!data.containsKey(Constants.DATA_TYPE) || data[Constants.DATA_TYPE] != Constants.REQUIRED_TYPE_CHECK) {
        Log.w(tag(), "Ignoring irrelevant notification")
        return null
    }

    val notificationId = data[Constants.DATA_ID]?.toIntOrNull() ?: generateNotificationId()
    val title = data[Constants.DATA_TITLE] ?: ""
    val message = data[Constants.DATA_MESSAGE] ?: ""

    val buttons = data[Constants.DATA_BUTTONS]?.let { parseNotificationButtons(it) }
    val color = data[Constants.DATA_COLOR]?.let { parseColor(it) } ?: Color.WHITE
    val deeplink = data[Constants.DATA_DEEPLINK]
    val template = data[Constants.DATA_TEMPLATE]

    val notification: Notification? = when (template) {
        Constants.TEMPLATE_LARGE -> data[Constants.DATA_IMAGE]?.let { image ->
            LargeNotification(
                id = notificationId,
                title = title,
                message = message,
                image = image,
                color = color,
                buttons = buttons,
                deeplink = deeplink
            )
        } ?: run {
            Log.w(tag(), "Missing $DATA_IMAGE, required for Large Image notification")
            null
        }

        Constants.TEMPLATE_CONVERSATION -> data[Constants.DATA_CONVERSATION]?.let { conversation ->
            ConversationNotification(
                id = notificationId,
                conversation = parseConversationMessages(conversation),
                color = color,
                buttons = buttons,
                deeplink = deeplink
            )
        } ?: run {
            Log.w(tag(), "Missing $DATA_CONVERSATION, required for conversation notification")
            null
        }

        Constants.TEMPLATE_BIG_TEXT -> BigTextNotification(
            id = notificationId,
            title = title,
            message = message,
            color = color,
            buttons = buttons,
            deeplink = deeplink
        )

        Constants.TEMPLATE_INBOX -> data[DATA_LINES]
            ?.let { parseInboxLines(it) }
            ?.let { lines ->
                InboxNotification(
                    id = notificationId,
                    title = title,
                    message = message,
                    lines = lines,
                    summeryText = data[Constants.SUMMERY_TEXT],
                    color = color,
                    buttons = buttons,
                    bigContentTitle = data[Constants.BIG_CONTENT_TITLE],
                    deeplink = deeplink
                )
            }
            ?: run {
                Log.w(tag(), "Missing $DATA_LINES, required for Inbox notification")
                null
            }

        else -> DefaultNotification(
            id = notificationId,
            title = title,
            message = message,
            color = color,
            buttons = buttons,
            deeplink = deeplink
        )
    }
    return notification
}

private fun parseConversationMessages(conversationJson: String): List<NotificationCompat.MessagingStyle.Message> {
    val messages = mutableListOf<NotificationCompat.MessagingStyle.Message>()

    try {
        val jsonArray = JSONArray(conversationJson)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val text = jsonObject.getString(Constants.JSON_TEXT)
            val timestamp = jsonObject.getLong(Constants.JSON_TIMESTAMP)
            val senderName = jsonObject.getString(Constants.JSON_SENDER)

            val person = Person.Builder().setName(senderName).build()
            val message = NotificationCompat.MessagingStyle.Message(text, timestamp, person)
            messages.add(message)
        }
    } catch (e: Exception) {
        Log.e("NotificationService", "Error parsing conversation messages: ${e.message}")
    }

    return messages
}

private fun parseInboxLines(linesJson: String): List<String> {
    val lines = mutableListOf<String>()

    try {
        val jsonArray = JSONArray(linesJson)

        for (i in 0 until jsonArray.length()) {
            lines.add(jsonArray.getString(i))
        }
    } catch (e: Exception) {
        Log.e("NotificationService", "Error parsing inbox lines: ${e.message}")
    }

    return lines
}

private fun parseNotificationButtons(buttonsJson: String): List<NotificationButton>? {
    val buttons = mutableListOf<NotificationButton>()
    try {
        val jsonArray = JSONArray(buttonsJson)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val text = jsonObject.getString("text")
            val deeplink = jsonObject.getString("deeplink")
            buttons.add(NotificationButton(text, deeplink))
        }
        return buttons.ifEmpty { null }
    } catch (e: Exception) {
        Log.e("NotificationService", "Error parsing notification buttons: ${e.message}")
    }
    return null
}