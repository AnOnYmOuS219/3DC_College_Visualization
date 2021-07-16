package com.example.collegeexploration.ui.vrimage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeexploration.R
import com.example.collegeexploration.utils.CommonUtils
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

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
//        val mediaId = intent.getIntExtra(getString(R.string.mediaId_extra_key), 0)
        val mediaUrl = intent.getStringExtra(getString(R.string.mediaId_extra_key))

        val option = VrPanoramaView.Options().also {
            it.inputType = VrPanoramaView.Options.TYPE_MONO
        }

//        val bitmap = BitmapFactory.decodeResource(getResources(), mediaId)
//        val bitmap = BitmapFactory.decodeResource(resources, mediaId)

        Picasso.get().load(mediaUrl).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                vrPanoramaView.loadImageFromBitmap(bitmap, option)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                TODO("Not yet implemented")
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                TODO("Not yet implemented")
            }

        })

    }
}