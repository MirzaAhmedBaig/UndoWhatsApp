package com.undowhatsapp.mirza.undowhatsapp

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log


/**
 * Created by Mirza-Ahmed on 23-04-2018.
 */
class NotificationService : NotificationListenerService() {
    private val TAG = NotificationService::class.java.simpleName

    private val LISTINING_PCK = "com.whatsapp"

    override fun onNotificationPosted(sbn: StatusBarNotification?) {

        if (sbn != null && sbn.packageName == LISTINING_PCK && sbn.notification.extras.get("android.title").toString() != "WhatsApp" && sbn.notification.extras.get("android.title").toString() != "WhatsApp Web" && !sbn.notification.extras.keySet().contains("android.textLines")) {
            val intent = Intent(packageName)
            intent.putExtra("android.title", sbn.notification.extras.get("android.title").toString())
            intent.putExtra("android.text", sbn.notification.extras.get("android.text").toString())
            intent.putExtra("android.time", System.currentTimeMillis())
            sendBroadcast(intent)

            Log.d(TAG, "#TAG User Name : " + sbn.notification.extras.get("android.title").toString())
            Log.d(TAG, "#TAG Message : " + sbn.notification.extras.get("android.text").toString())
        }


        /*for (key in sbn!!.notification.extras.keySet()) {
            Log.d(TAG, "#TAG $key =  ${sbn!!.notification.extras.get(key)}")
        }*/
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }

}