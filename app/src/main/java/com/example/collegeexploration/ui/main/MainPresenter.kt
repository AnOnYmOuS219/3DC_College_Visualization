package com.example.collegeexploration.ui.main

import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.ui.base.BasePresenter

class MainPresenter<V : MainMvpView>(mDataManager: DataManager) : BasePresenter<V>(mDataManager), MainMvpPresenter<V> {

}