package com.example.collegeexploration.ui.vr

import androidx.viewpager2.widget.ViewPager2
import com.example.collegeexploration.ui.base.MvpView
import com.google.android.material.tabs.TabLayout

interface VRMvpView : MvpView {

    fun registerViewPages(tabLayout: TabLayout, viewPager2: ViewPager2)
}