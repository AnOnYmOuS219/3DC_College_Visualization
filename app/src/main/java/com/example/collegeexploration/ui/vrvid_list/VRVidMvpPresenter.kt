package com.example.collegeexploration.ui.vrvid_list

import com.example.collegeexploration.ui.base.MvpPresenter

interface VRVidMvpPresenter<V: VRVidMvpView>: MvpPresenter<V> {

    fun setAdapterToRV()
}