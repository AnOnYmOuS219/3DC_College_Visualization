package com.example.collegeexploration.ui.vr

import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.ui.base.BasePresenter

class VRPresenter<V : VRMvpView>(mDataManager: DataManager) : BasePresenter<V>(mDataManager), VRMvpPresenter<V> {
}