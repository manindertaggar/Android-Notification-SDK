package co.taggar.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Service for handling Firebase Cloud Messaging notifications.
 * This class processes incoming notifications with different templates
 * such as large images, conversations, big text, and inbox styles.
 */
class NotificationService : FirebaseMessagingService() {
    private val messageHandler by lazy { RemoteMessageHandler(this) }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(tag(), "Refreshed token: $token")
    }

    /**
     * Called when a message is received from FCM.
     * @param remoteMessage The received message from FCM.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(tag(), "Message received from: ${remoteMessage.from}")

        remoteMessage.toNotification()?.let { notification ->
            messageHandler.handleMessage(notification)
        }?.run {
            Log.w(tag(), "Couldn't obtain instance of notification object")
        }
    }
}