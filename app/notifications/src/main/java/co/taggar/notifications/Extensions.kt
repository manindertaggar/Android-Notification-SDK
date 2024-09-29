package co.taggar.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent


fun Any.Tag() = this.javaClass.simpleName.toString()


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