package com.example.collegeexploration.ui.vr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.collegeexploration.R
import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.ui.vr.vrutils.FragmentAdapter
import com.google.android.material.tabs.TabLayout

/**
 * VR Fragment
 */
class VRFragment(val mDataManager: DataManager) : Fragment(R.layout.fragment_v_r), VRMvpView {

    private lateinit var mPresenter: VRMvpPresenter<VRMvpView>
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager2 : ViewPager2
    private lateinit var mFragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = VRPresenter(mDataManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onAttachView(this)

        mTabLayout = view.findViewById(R.id.tab_layout)
        mViewPager2 = view.findViewById(R.id.view_pager2)

        mFragmentAdapter = FragmentAdapter(fragmentManager, lifecycle, mDataManager)
        mViewPager2.adapter = mFragmentAdapter

        registerViewPages(mTabLayout, mViewPager2)
    }

    override fun registerViewPages(tabLayout: TabLayout, viewPager2: ViewPager2) {

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_image)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_video)))

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(mTabLayout.getTabAt(position))
            }
        })
    }
}