package co.taggar.example

import androidx.core.app.NotificationCompat
import co.taggar.notifications.models.NotificationButton
import co.taggar.notifications.models.styles.ConversationNotification

object FCMTestData {

    // Create a list of MessagingStyle messages
    private val conversationMessages = listOf(
        NotificationCompat.MessagingStyle.Message(
            "Hey, how's it going?",
            1634567890123L,
            "Alice"
        ),
        NotificationCompat.MessagingStyle.Message(
            "I'm good! What about you?", 1634567891123L, "Bob"
        )
    )

    // Create a list of notification buttons
    private val buttons = listOf(
        NotificationButton(
            text = "Open App",
            deeplink = "app://open"
        ),
        NotificationButton(
            text = "Email",
            deeplink = "mailto:ahdev2020@outlook.com"
        )
    )

    // Create a ConversationNotification object
    val conversationNotification = ConversationNotification(
        conversation = conversationMessages,
        id = 101,
        color = 0xFF5733, // Hex color converted to integer
        buttons = buttons,
        deeplink = "https://example.com"
    )
}
