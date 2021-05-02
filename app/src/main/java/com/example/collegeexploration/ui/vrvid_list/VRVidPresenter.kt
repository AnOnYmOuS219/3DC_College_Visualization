package com.example.collegeexploration.ui.vrvid_list

import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.ui.base.BasePresenter

class VRVidPresenter<V: VRVidMvpView>(mDataManager: DataManager) : BasePresenter<V>(mDataManager),
    VRVidMvpPresenter<V> {

    override fun setAdapterToRV() {
        val vidList: List<MediaItem> = mDataManager.getVRVidList()
        getMvpView().settingUpRecyclerView(vidList)
    }
}