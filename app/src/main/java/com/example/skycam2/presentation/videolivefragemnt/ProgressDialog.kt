package com.example.skycam2.presentation.videolivefragemnt

import android.app.AlertDialog
import android.content.Context
import com.example.skycam2.R
import org.videolan.libvlc.Dialog

class ProgressDialog(context: Context) {

    private var delayMs: Int = 1000
    private var isVisible: Boolean = false
    private var starTime: Long = 0

    private var dialog: AlertDialog = AlertDialog.Builder(context)
        .setView(R.layout.item_loading)
        .setCancelable(false)
        .create()

    fun show(){
        if (isVisible){
            return
        }
        if (starTime == 0L){
            starTime = System.currentTimeMillis()
        }
        if (System.currentTimeMillis() - starTime > delayMs){
            isVisible = true
            dialog.show()
        }
    }

    fun hide(){
        starTime = 0
        isVisible = false
        dialog.hide()
    }
}