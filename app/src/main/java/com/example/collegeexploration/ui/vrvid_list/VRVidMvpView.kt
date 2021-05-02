package com.example.collegeexploration.ui.vrvid_list

import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.ui.base.MvpView

interface VRVidMvpView: MvpView {

    fun settingUpRecyclerView(vidList: List<MediaItem>)
}