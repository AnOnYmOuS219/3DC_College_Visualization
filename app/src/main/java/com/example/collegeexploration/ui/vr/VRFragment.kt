package com.example.collegeexploration.ui.vr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.collegeexploration.R
import com.example.collegeexploration.data.DataManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class VRFragment(val mDataManager: DataManager) : Fragment(), VRMvpView {

    private lateinit var mPresenter: VRMvpPresenter<VRMvpView>
//    private lateinit var mRecyclerView : RecyclerView
//    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager2 : ViewPager2
    private lateinit var mFragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = VRPresenter(mDataManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_v_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onAttachView(this)

//        mRecyclerView = view.findViewById(R.id.rv_vrlist)
//        mBottomNavigationView = view.findViewById(R.id.bnv_vr)

//        mBottomNavigationView.setOnNavigationItemSelectedListener {
//            changeData(it.itemId)
//            return@setOnNavigationItemSelectedListener true
//        }

        mTabLayout = view.findViewById(R.id.tab_layout)
        mViewPager2 = view.findViewById(R.id.view_pager2)

        mFragmentAdapter = FragmentAdapter(fragmentManager, lifecycle)
        mViewPager2.adapter = mFragmentAdapter

        mTabLayout.addTab(mTabLayout.newTab().setText("Image"))
        mTabLayout.addTab(mTabLayout.newTab().setText("Videos"))

        mTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    mViewPager2.setCurrentItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        mViewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                mTabLayout.selectTab(mTabLayout.getTabAt(position))
            }
        })
    }

    override fun changeData(itemId: Int) {

        when(itemId){
            R.id.item_vrimg -> "Images Fragment"
            R.id.item_vrvid -> "Videos Fragment"
            else -> ""
        }

    }
}