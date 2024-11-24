package co.taggar.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import co.taggar.notifications.Constants.CHANNEL_NAME
import co.taggar.notifications.models.NotificationButton
import co.taggar.notifications.models.styles.BigTextNotification
import co.taggar.notifications.models.styles.ConversationNotification
import co.taggar.notifications.models.styles.DefaultNotification
import co.taggar.notifications.models.styles.InboxNotification
import co.taggar.notifications.models.styles.LargeNotification
import co.taggar.notifications.models.styles.Notification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class RemoteMessageHandler(private val context: Context) {
    fun handleMessage(notification: Notification) {
        Log.d(tag(), "Handling notification $notification")
        when (notification) {
            is DefaultNotification -> handleDefaultNotification(notification)
            is LargeNotification -> handleBigPictureNotification(notification)
            is BigTextNotification -> handleBigTextNotification(notification)
            is InboxNotification -> handleInboxNotification(notification)
            is ConversationNotification -> handleConversationNotification(notification)
        }
    }

    private fun handleDefaultNotification(
        notification: DefaultNotification
    ) {
        Log.d(tag(), "Sending default notification")
        notification.apply {
            val notificationBuilder =
                createBaseNotificationBuilder(title, message, deeplink, color, buttons)
            notify(id, notificationBuilder)
        }
    }

    private fun handleBigPictureNotification(
        notification: LargeNotification
    ) {
        notification.apply {
            Log.d(tag(), "Handling big picture notification")
            CoroutineScope(Dispatchers.IO).launch {
                val bitmap = downloadImage(image)
                bitmap?.let {
                    Log.d(tag(), "Sending big picture notification")
                    val notificationBuilder =
                        createBaseNotificationBuilder(title, message, deeplink, color, buttons)
                            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                    notify(id, notificationBuilder)
                }
            }
        }
    }

    private suspend fun downloadImage(url: String): Bitmap? = withContext(Dispatchers.IO) {
        Log.d(tag(), "Downloading image from URL: $url")
        return@withContext try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.instanceFollowRedirects = true  // Allow redirects
            connection.doInput = true
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                BitmapFactory.decodeStream(inputStream)
            } else {
                Log.e(tag(), "Failed to download image: Server returned ${connection.responseCode}")
                null
            }
        } catch (e: IOException) {
            Log.e(tag(), "Failed to download image: $e")
            null
        }
    }

    private fun handleConversationNotification(
        notification: ConversationNotification
    ) {
        notification.apply {
            Log.d(tag(), "Handling conversation notification")
            val messagingStyle =
                NotificationCompat.MessagingStyle(Person.Builder().setName("me").build())
            conversation.forEach { messagingStyle.addMessage(it) }

            val notificationBuilder =
                createBaseNotificationBuilder(title, null, deeplink, color, buttons)
                    .setStyle(messagingStyle)

            notify(generateNotificationId(), notificationBuilder)
        }
    }

    private fun handleBigTextNotification(
        notification: BigTextNotification
    ) {
        Log.d(tag(), "Handling big text notification")
        notification.apply {
            val notificationBuilder =
                createBaseNotificationBuilder(title, message, deeplink, color, buttons)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            notify(id, notificationBuilder)
        }
    }


    private fun handleInboxNotification(
        notification: InboxNotification
    ) {
        notification.apply {
            Log.d(tag(), "Handling inbox style notification")
            val inboxStyle = NotificationCompat.InboxStyle()
            notification.lines.forEach { inboxStyle.addLine(it) }
            inboxStyle.setSummaryText(summeryText)
            inboxStyle.setBigContentTitle(bigContentTitle)

            val notificationBuilder =
                createBaseNotificationBuilder(title, message, deeplink, color, buttons)
                    .setStyle(inboxStyle)
            notify(id, notificationBuilder)
        }
    }

    private fun parseColor(colorHex: String): Int {
        return try {
            Color.parseColor(colorHex)
        } catch (e: IllegalArgumentException) {
            Color.WHITE
        }
    }

    private fun createBaseNotificationBuilder(
        title: String?,
        message: String?,
        deeplink: String?,
        color: Int,
        buttons: List<NotificationButton>?
    ): NotificationCompat.Builder {
        Log.d(tag(), "Creating base notification builder")
        val channelId = getChannelId()

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setColor(color)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        deeplink?.let { builder.setContentIntent(getDeeplinkIntent(it)) }

        if (isSoundOn()) {
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        }

        buttons?.forEach { (text, deeplink) ->
            builder.addAction(0, text, getDeeplinkIntent(deeplink))
        }
        return builder
    }

    private fun notify(notificationId: Int, builder: NotificationCompat.Builder) {
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, builder.build())
    }

    fun deleteNotification(notificationId: Int) {
        NotificationManagerCompat.from(context).cancel(notificationId)
    }

    private fun getChannelId(): String {
        val channelId = Constants.CHANNEL_ID
        val notificationManager = NotificationManagerCompat.from(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(channelId) == null) {
                val serviceChannel = NotificationChannel(
                    channelId,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(serviceChannel)
            }
        }
        return channelId
    }

    private fun isSoundOn(): Boolean {
        // Implement actual logic to check user settings/preferences
        return true
    }

    private fun getDeeplinkIntent(deeplink: String): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}