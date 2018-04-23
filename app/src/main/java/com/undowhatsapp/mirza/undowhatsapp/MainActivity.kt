package com.undowhatsapp.mirza.undowhatsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log


class MainActivity : AppCompatActivity() {

    private val NOTIFICATION_ACTION = "com.undowhatsapp.mirza.undowhatsapp"

    private val TAG = MainActivity::class.java.simpleName

    private val notificationReceiver by lazy {
        NotificationReceiver()
    }

    private val intentFilter by lazy {
        IntentFilter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        intentFilter.addAction(NOTIFICATION_ACTION)
        registerReceiver(notificationReceiver, intentFilter)
    }


    internal inner class NotificationReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, intent.getStringExtra("title"))

        }
    }
}
