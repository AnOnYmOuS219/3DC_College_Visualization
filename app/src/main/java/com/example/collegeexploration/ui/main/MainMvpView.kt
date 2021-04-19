package com.example.collegeexploration.ui.main

import com.example.collegeexploration.ui.base.MvpView

interface MainMvpView : MvpView {

    fun changeFragment(itemId: Int)
}