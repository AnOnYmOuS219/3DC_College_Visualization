package com.example.collegeexploration.ui.vrimg

import com.example.collegeexploration.ui.base.MvpPresenter

interface VRImgMvpPresenter<V: VRImgMvpView>: MvpPresenter<V> {

    fun setAdapterToRV()
}