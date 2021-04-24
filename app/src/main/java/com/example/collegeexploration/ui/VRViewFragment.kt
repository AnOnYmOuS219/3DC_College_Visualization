package com.example.collegeexploration.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.collegeexploration.R
import com.google.vr.sdk.widgets.pano.VrPanoramaView

class VRViewFragment(val mediaId: Int) : Fragment(R.layout.fragment_v_r_view) {

    private lateinit var vrPanoramaView: VrPanoramaView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vrPanoramaView = view.findViewById(R.id.vrPanoramaView)

        val option = VrPanoramaView.Options().also {
            it.inputType = VrPanoramaView.Options.TYPE_MONO
        }

        val bitmap = BitmapFactory.decodeResource(getResources(), mediaId)
        vrPanoramaView.loadImageFromBitmap(bitmap, option)
    }
}