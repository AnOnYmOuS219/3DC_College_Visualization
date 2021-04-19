package com.example.collegeexploration.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.collegeexploration.MvpApp
import com.example.collegeexploration.R
import com.example.collegeexploration.ui.main.fragments.ARFragment
import com.example.collegeexploration.ui.main.fragments.VRFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), MainMvpView{

    private lateinit var mARVRBottomNavigationView: BottomNavigationView
    private lateinit var mPresenter: MainPresenter<MainMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mvpApp: MvpApp = applicationContext as MvpApp
        mPresenter = MainPresenter(mvpApp.getDataManager())
        mPresenter.onAttachView(this)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_fragment, ARFragment())
            addToBackStack(null)
            commit()
        }

        mARVRBottomNavigationView = findViewById(R.id.bnv_arvr)
        mARVRBottomNavigationView.setOnNavigationItemSelectedListener {
            changeFragment(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun changeFragment(itemId: Int) {

        var fragment: Fragment = if(itemId == R.id.item_ar) ARFragment() else VRFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}