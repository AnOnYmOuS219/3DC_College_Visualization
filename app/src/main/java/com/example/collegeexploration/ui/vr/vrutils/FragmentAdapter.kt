package com.example.collegeexploration.ui.vr.vrutils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.ui.vrimg.VRImgFragment
import com.example.collegeexploration.ui.vrvid.VRVidFragment

class FragmentAdapter(fragmentManger : FragmentManager?, lifecycle : Lifecycle, val dataManager: DataManager) :
    FragmentStateAdapter(fragmentManger!!, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> VRImgFragment(dataManager)
            1 -> VRVidFragment()
            else -> VRImgFragment(dataManager)
        }
    }
}