package com.example.collegeexploration.utils

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.widget.Toast

class CommonUtils {

    companion object{
        fun printLog(tag: String, message: String){
            Log.v(tag, message)
        }

        fun printToast(context: Context?, message: String){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        
        fun hideStatusBar(window: Window){
            // Hide the status bar.
            if (Build.VERSION.SDK_INT < 30) {
                window.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            } else {
                window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
            }
        }
    }
}