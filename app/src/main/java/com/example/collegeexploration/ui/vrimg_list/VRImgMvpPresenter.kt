package com.example.collegeexploration.ui.vrimg_list

import com.example.collegeexploration.ui.base.MvpPresenter

interface VRImgMvpPresenter<V: VRImgMvpView>: MvpPresenter<V> {

    fun setAdapterToRV()
}