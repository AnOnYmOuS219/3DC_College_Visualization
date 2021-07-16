package com.example.collegeexploration.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.collegeexploration.MvpApp
import com.example.collegeexploration.R
import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.events.VREvent
import com.example.collegeexploration.events.VREventFirebase
import com.example.collegeexploration.ui.vrimage.VRImageActivity
import com.example.collegeexploration.ui.vrvideo.VRVideoActivity
import com.example.collegeexploration.ui.ar.ARFragment
import com.example.collegeexploration.ui.vr.VRFragment
import com.example.collegeexploration.utils.CommonUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Main Activity holds a layout of bottom navigation of AR & VR
 * Hosts AR fragment & VR fragment
 */
class MainActivity : AppCompatActivity(), MainMvpView {

    private lateinit var mARVRBottomNavigationView: BottomNavigationView
    private lateinit var mPresenter: MainPresenter<MainMvpView>
    private lateinit var mDataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CommonUtils.hideStatusBar(window)

        val mvpApp: MvpApp = applicationContext as MvpApp
        mDataManager = mvpApp.getDataManager()
        // MainPresenter
        mPresenter = MainPresenter(mDataManager)
        mPresenter.onAttachView(this)

        // register EventBus
        EventBus.getDefault().register(this)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_fragment, ARFragment())
            commit()
        }

        mARVRBottomNavigationView = findViewById(R.id.bnv_arvr)
        mARVRBottomNavigationView.setOnNavigationItemSelectedListener {
            changeFragment(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun changeFragment(itemId: Int) {

        var fragment: Fragment =
            if (itemId == R.id.item_ar) ARFragment() else VRFragment(mDataManager)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_fragment, fragment)
            commit()
        }
    }

    @Subscribe
    fun handleVREvent(vrEvent: VREvent) {
        if (vrEvent.mediaItem.mediaTagImg) {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.frame_fragment, VRViewFragment(vrEvent.mediaItem.mediaId))
//                commit()
//            }
            val intent: Intent = Intent(this, VRImageActivity::class.java).apply {
                putExtra(getString(R.string.mediaId_extra_key), vrEvent.mediaItem.mediaId)
            }
            startActivity(intent)
        } else {
            val intent: Intent = Intent(this, VRVideoActivity::class.java).apply {
                putExtra(getString(R.string.mediaId_extra_key), vrEvent.mediaItem.mediaId)
            }
            startActivity(intent)
        }
    }

    @Subscribe
    fun handleVREvent(vrEvent: VREventFirebase) {

        val intent: Intent = Intent(this, VRImageActivity::class.java).apply {
            putExtra(getString(R.string.mediaId_extra_key), vrEvent.mediaItem.url)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}