package co.taggar.example

import android.os.Bundle
import com.google.firebase.messaging.RemoteMessage

object FCMTestData {

    // Create a Bundle that simulates the notification data
    private val bundle = Bundle().apply {
        putString("type", "ANDP")
        putString("id", "101")
        putString("title", "Test Notification")
//        putString(
//            "message",
//            "This is a test message from Android code. and the long text should be supported here."
//        )
        putString("color", "#FF5733")
        putString("deeplink", "https://example.com")
        putString("template", "CONVERSATION")
//        putString(
//            "lines",
//            "[\"line 1\",\"line 2\",\"line 3\",\"line 4\"]"
//        )
        putString("summery_text", "Emails")
        putString("image", "https://picsum.photos/200/300")
        putString("conversation", "[\n" +
                "  {\n" +
                "    \"text\": \"Hey, how's it going?\",\n" +
                "    \"timestamp\": 1634567890123,\n" +
                "    \"sender\": \"Alice\"\n" +
                "  },\n"+
                "]")
        putString(
            "buttons",
            "[{\"text\": \"Open App\", \"deeplink\": \"app://open\"}, {\"text\": \"Email\", \"deeplink\": \"mailto:ahdev2020@outlook.com\"}]"
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