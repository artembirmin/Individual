package com.example.individual.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

object DialogUtils {
    fun showMessageByAlertDialog(
        context: Context,
        title: String? = null,
        message: String? = null,
        positiveText: String? = "Ok",
        negativeText: String? = null,
        onPositiveButtonClick: (() -> Unit)? = null,
        onNegativeButtonClick: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .apply {
                if (negativeText != null) {
                    setNegativeButton(negativeText) { _, _ -> onNegativeButtonClick?.invoke() }
                }
                if (title != null) {
                    setTitle(title)
                }
            }
            .setPositiveButton(positiveText) { _, _ -> onPositiveButtonClick?.invoke() }
            .setOnDismissListener { onDismiss?.invoke() }
            .create()
            .show()
    }

    fun showMessageByToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

}