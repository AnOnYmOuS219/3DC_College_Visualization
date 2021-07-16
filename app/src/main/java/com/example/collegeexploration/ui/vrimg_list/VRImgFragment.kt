package com.example.collegeexploration.ui.vrimg_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R
import com.example.collegeexploration.data.DataManager
import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.data.MediaItemFireBase
import com.example.collegeexploration.ui.vr.recyclerview.MediaFirebaseViewHolder
import com.example.collegeexploration.ui.vr.recyclerview.VRListAdapter
import com.example.collegeexploration.ui.vr.recyclerview.VRListFirebaseAdapter
import com.example.collegeexploration.utils.CommonUtils
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*

/**
 * VR Images List Fragment
 */
class VRImgFragment(val mDataManager: DataManager) : Fragment(R.layout.fragment_v_r_img), VRImgMvpView {

    private lateinit var mPresenter: VRImgPresenter<VRImgMvpView>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMediaItems: MutableList<MediaItemFireBase>
    private lateinit var mVRListAdapter: VRListFirebaseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = VRImgPresenter(mDataManager)
        mPresenter.onAttachView(this)
        mRecyclerView = view.findViewById(R.id.recycler_view_vrimg)

        val firebaseDb = FirebaseDatabase.getInstance()
        val dbRef = firebaseDb.getReference("media")
        CommonUtils.printLog("peaches", "dbref = " + dbRef)
        dbRef.setValue("hi")
//        val query = dbRef!!.limitToLast(8)

//        val firebaseRVAdapter = object : FirebaseRecyclerAdapter<MediaItemFireBase, MediaFirebaseViewHolder>(
//            MediaItemFireBase::class.java, R.layout.media_item,
//            MediaFirebaseViewHolder::class.java, query) {
//            override fun populateViewHolder(
//                p0: MediaFirebaseViewHolder?,
//                p1: MediaItemFireBase?,
//                p2: Int
//            ) {
//                p0!!.bind(p1!!)
//            }
//
//        }
        mMediaItems = mutableListOf()
        dbRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                System.out.println("hi snapshot = " + snapshot)
                for(item in snapshot.children) {
                    val mediaItem = item.getValue(MediaItemFireBase::class.java)
                    mediaItem?.let{
                        mMediaItems.add(it)
                    }
                }
                mVRListAdapter = VRListFirebaseAdapter(VRListFirebaseAdapter.createDiffClass())
                settingUpRecyclerViewv2(mMediaItems)
            }

            override fun onCancelled(error: DatabaseError) {
                CommonUtils.printToast(context, "Check your internet connection!")
            }

        })

//        mRecyclerView.adapter = firebaseRVAdapter
////        mRecyclerView.layoutManager = LinearLayoutManager(this.context)

//        mVRListAdapter = VRListAdapter(VRListAdapter.Companion.createDiffClass())
//        mPresenter.setAdapterToRV()
    }

    override fun settingUpRecyclerView(imgList: List<MediaItem>) {
        mRecyclerView.adapter = mVRListAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
//        mVRListAdapter.submitList(imgList)
    }

    fun settingUpRecyclerViewv2(imgList: List<MediaItemFireBase>) {
        mRecyclerView.adapter = mVRListAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mVRListAdapter.submitList(imgList)
    }
}