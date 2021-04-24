package com.example.collegeexploration.ui.vrimg

import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.ui.base.MvpView

interface VRImgMvpView: MvpView {

    fun settingUpRecyclerView(imgList: List<MediaItem>)
}