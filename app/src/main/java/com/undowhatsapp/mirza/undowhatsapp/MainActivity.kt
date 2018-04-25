package com.undowhatsapp.mirza.undowhatsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.undowhatsapp.mirza.undowhatsapp.adapters.MessageListAdapter
import com.undowhatsapp.mirza.undowhatsapp.extensions.showConfirmDialog
import com.undowhatsapp.mirza.undowhatsapp.models.MessageModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val NOTIFICATION_ACTION = "com.undowhatsapp.mirza.undowhatsapp"

    private val TAG = MainActivity::class.java.simpleName

    private val intentFilter by lazy {
        IntentFilter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        val allowedAccessList = NotificationManagerCompat.getEnabledListenerPackages(this)
        if (allowedAccessList.contains(packageName)) {
            Log.d(TAG, "Notification allowed ")
            intentFilter.addAction(packageName)
            registerReceiver(notificationReceiver, intentFilter)
            setMessageAdapter()
        } else {
            showConfirmDialog("Please Enable Notification Access", "For the app. to work you need to enable Notification Access. Enable it now ? ", { dialog ->
                startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
                dialog.dismiss()
            }, { dialog ->
                dialog.dismiss()
                finish()
            })
        }
        super.onResume()
    }

    override fun onPause() {
        try {
            unregisterReceiver(notificationReceiver)
        } catch (e: Exception) {
        }
        super.onPause()
    }

    private fun setMessageAdapter() {
        val list = ArrayList<MessageModel>()
        for (i in 0 until 10) {
            list.add(MessageModel("User $i", "Message $i", i.toLong()))
        }
        deleted_msg_list.layoutManager = LinearLayoutManager(this)
        deleted_msg_list.adapter = MessageListAdapter(list)
    }

    private val notificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            Log.d(TAG, "Received PKG Name : ${intent.getStringExtra("package_name")}")

        }
    }
}
