package com.example.collegeexploration.ui.vrimg_list

import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.ui.base.MvpView

interface VRImgMvpView: MvpView {

    fun settingUpRecyclerView(imgList: List<MediaItem>)
}