package com.example.collegeexploration.ui.vr

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.collegeexploration.ui.VRImgFragment
import com.example.collegeexploration.ui.VRVidFragment

class FragmentAdapter(fragmentManger : FragmentManager?, lifecycle : Lifecycle) :
    FragmentStateAdapter(fragmentManger!!, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> VRImgFragment()
            1 -> VRVidFragment()
            else -> VRImgFragment()
        }
    }
}