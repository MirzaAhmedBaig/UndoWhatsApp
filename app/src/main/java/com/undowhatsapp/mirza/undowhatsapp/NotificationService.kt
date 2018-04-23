package com.undowhatsapp.mirza.undowhatsapp

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

/**
 * Created by Mirza-Ahmed on 23-04-2018.
 */
class NotificationService : NotificationListenerService() {
    private val TAG = NotificationService::class.java.simpleName
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d(TAG, "ID:" + sbn!!.id)
        Log.d(TAG, "Posted by:" + sbn.packageName)
        Log.d(TAG, "tickerText:" + sbn.notification.tickerText)

        for (key in sbn.notification.extras.keySet()) {
            Log.d(TAG, key + "=" + sbn.notification.extras.get(key).toString())
        }

    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }
}