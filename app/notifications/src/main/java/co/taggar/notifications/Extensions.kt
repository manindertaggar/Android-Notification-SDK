package co.taggar.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent


fun Any.tag() = this.javaClass.simpleName.toString()


fun Context.getRestartPendingIntent(): PendingIntent {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    val componentName = intent?.component
    val mainIntent = Intent.makeRestartActivityTask(componentName)

    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val requestCode = 0
    val pendingIntent = PendingIntent.getActivity(
        this, requestCode, mainIntent,
        PendingIntent.FLAG_IMMUTABLE,
    )
    return pendingIntent
}


/**
 * Generates a unique notification ID based on the current system time.
 * @return A unique notification ID.
 */
fun generateNotificationId(): Int {
    return (System.currentTimeMillis() and 0xFFFFFFF).toInt()
}
