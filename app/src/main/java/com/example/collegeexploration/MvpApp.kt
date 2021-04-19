package com.example.collegeexploration

import android.app.Application
import com.example.collegeexploration.data.DataManager

class MvpApp : Application() {

    private lateinit var mDataManager: DataManager

    override fun onCreate() {
        super.onCreate()

        mDataManager = DataManager()
    }

    fun getDataManager() : DataManager{
        return mDataManager
    }
}