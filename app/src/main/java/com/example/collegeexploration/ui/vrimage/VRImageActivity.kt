package com.example.collegeexploration.ui.vrimage

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeexploration.R
import com.example.collegeexploration.utils.CommonUtils
import com.google.vr.sdk.widgets.pano.VrPanoramaView

/**
 * Shows an image in a VR format
 */
class VRImageActivity : AppCompatActivity() {

    private lateinit var vrPanoramaView: VrPanoramaView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vr_image)

        CommonUtils.hideStatusBar(window)

        vrPanoramaView = findViewById(R.id.panorama_view_image)
        val mediaId = intent.getIntExtra(getString(R.string.mediaId_extra_key), 0)

        val option = VrPanoramaView.Options().also {
            it.inputType = VrPanoramaView.Options.TYPE_MONO
        }

//        val bitmap = BitmapFactory.decodeResource(getResources(), mediaId)
        val bitmap = BitmapFactory.decodeResource(resources, mediaId)
        vrPanoramaView.loadImageFromBitmap(bitmap, option)
    }
}