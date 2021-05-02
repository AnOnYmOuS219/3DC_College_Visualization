package com.example.collegeexploration.ui.vrimg_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R
import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.ui.vr.recyclerview.VRListAdapter

class VRImgFragment(val mDataManager: DataManager) : Fragment(R.layout.fragment_v_r_img), VRImgMvpView {

    private lateinit var mPresenter: VRImgPresenter<VRImgMvpView>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mVRListAdapter: VRListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = VRImgPresenter(mDataManager)
        mPresenter.onAttachView(this)
        mRecyclerView = view.findViewById(R.id.recycler_view_vrimg)

        mVRListAdapter = VRListAdapter(VRListAdapter.Companion.createDiffClass())
        mPresenter.setAdapterToRV()
    }

    override fun settingUpRecyclerView(imgList: List<MediaItem>) {
        mRecyclerView.adapter = mVRListAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mVRListAdapter.submitList(imgList)
    }
}