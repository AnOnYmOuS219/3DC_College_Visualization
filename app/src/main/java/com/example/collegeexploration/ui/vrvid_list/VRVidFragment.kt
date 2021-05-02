package com.example.collegeexploration.ui.vrvid_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R
import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.ui.vr.recyclerview.VRListAdapter

class VRVidFragment(val mDataManager: DataManager) : Fragment(R.layout.fragment_v_r_vid), VRVidMvpView {

    private lateinit var mPresenter: VRVidPresenter<VRVidMvpView>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mVRListAdapter: VRListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = VRVidPresenter(mDataManager)
        mPresenter.onAttachView(this)
        mRecyclerView = view.findViewById(R.id.recycler_view_vrvid)

        mVRListAdapter = VRListAdapter(VRListAdapter.Companion.createDiffClass())
        mPresenter.setAdapterToRV()
    }

    override fun settingUpRecyclerView(vidList: List<MediaItem>) {
        mRecyclerView.adapter = mVRListAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mVRListAdapter.submitList(vidList)
    }
}