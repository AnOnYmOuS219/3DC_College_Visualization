package com.example.collegeexploration.ui.base

interface MvpPresenter<V : MvpView> {

    fun onAttachView(view: V)
}