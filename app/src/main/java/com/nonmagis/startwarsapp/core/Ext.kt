package com.nonmagis.startwarsapp.core

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

object PromptUtils {
    fun alertDialog(context: Context, dialogBuilder: AlertDialog.Builder.() -> Unit): Dialog {
        val builder = AlertDialog.Builder(context).also {
            it.setCancelable(false)
            it.dialogBuilder()
        }
        return builder.create()
    }

    fun AlertDialog.Builder.negativeButton(text: String = "Нет",
                                           handleClick: (dialogInterface: DialogInterface)
                                           -> Unit = {}) {
        this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
    }

    fun AlertDialog.Builder.positiveButton(text: String = "Да",
                                           handleClick: (dialogInterface: DialogInterface)
                                           -> Unit = {}) {
        this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
    }
}