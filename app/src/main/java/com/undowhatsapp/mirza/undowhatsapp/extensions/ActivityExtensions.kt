package com.undowhatsapp.mirza.undowhatsapp.extensions

import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.undowhatsapp.mirza.undowhatsapp.R

fun AppCompatActivity.showConfirmDialog(title: String, message: String, actionPositive: (dialog: DialogInterface) -> Unit, actionNegative: (dialog: DialogInterface) -> Unit = {}) {
    val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        AlertDialog.Builder(this, R.style.AlertDialogCustom)
    } else {
        AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog)
    }
    builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton(/*getCancel(langFlag)*/"Cancel", { dialog, id ->
                actionNegative.invoke(dialog)
            })
            .setPositiveButton(/*getOk(langFlag)*/"OK", { dialog, id ->
                actionPositive.invoke(dialog)
            })
            .show()
}

fun Any.toStringWithGson(): String {
    return Gson().toJson(this)
}