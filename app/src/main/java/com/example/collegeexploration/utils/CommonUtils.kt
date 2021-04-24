package com.example.collegeexploration.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

class CommonUtils {

    companion object{

        fun printLog(tag: String, message: String){

            Log.v(tag, message)
        }
        fun printToast(context: Context?, message: String){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}