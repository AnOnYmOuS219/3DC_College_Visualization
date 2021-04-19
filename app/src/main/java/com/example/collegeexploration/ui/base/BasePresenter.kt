package com.example.collegeexploration.ui.base

import com.example.collegeexploration.data.DataManager

open class BasePresenter<V: MvpView>(val mDataManager: DataManager) : MvpPresenter<V> {

    private lateinit var mView: V

    override fun onAttachView(view: V) {
        this.mView = view
    }
}