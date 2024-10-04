package co.taggar.example

import android.os.Bundle
import com.google.firebase.messaging.RemoteMessage

object FCMTestData {

    // Create a Bundle that simulates the notification data
    private val bundle = Bundle().apply {
        putString("type", "ANDP")
        putString("id", "101")
        putString("title", "Test Notification")
        putString("message", "This is a test message from Android code.")
        putString("color", "#FF5733")
        putString("deeplink", "https://example.com")
        putString("template", "LARGE")
        putString("image", "https://via.placeholder.com/600x400")
        putString(
            "buttons",
            "[{\"text\": \"Open App\", \"deeplink\": \"app://open\"}, {\"text\": \"Dismiss\", \"deeplink\": \"app://dismiss\"}]"
        )
    }

    val remoteMessage = createRemoteMessage(bundle)

    private fun createRemoteMessage(bundle: Bundle): RemoteMessage {
        // RemoteMessage.Builder allows you to simulate receiving a message
        return RemoteMessage.Builder("test_sender_id")
            .setData(bundleToMap(bundle))
            .build()
    }

    private fun bundleToMap(bundle: Bundle): Map<String, String> {
        val map = mutableMapOf<String, String>()
        for (key in bundle.keySet()) {
            map[key] = bundle.getString(key) ?: ""
        }
        return map
    }
}