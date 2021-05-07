package com.example.collegeexploration.ui.vrvideo

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeexploration.R
import com.example.collegeexploration.utils.CommonUtils
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource

/**
 * Shows a 360 degree video
 */
class VRVideoActivity : AppCompatActivity() {

    private var mPlayer: SimpleExoPlayer? = null
    private lateinit var mPlayerView: PlayerView
    private var mIntentMediaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vr_video)

        mPlayerView = findViewById(R.id.exo_player_vr)

        (mPlayerView.videoSurfaceView as SphericalGLSurfaceView)
            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)

        mIntentMediaId = intent.getIntExtra(getString(R.string.mediaId_extra_key), -1)
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this,
            getString(R.string.data_source_factory_agent))

        // Create a media source using the supplied URI
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(uri))
    }

    private fun initializePlayer() {
        mPlayer = SimpleExoPlayer.Builder(this).build()

//        val uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-1/360/congo.mp4")
        val uri = RawResourceDataSource.buildRawResourceUri(mIntentMediaId)
        val mediaSource = buildMediaSource(uri)
        mPlayer?.setMediaSource(mediaSource)

        mPlayerView.player = mPlayer
        mPlayerView.onResume()
    }

    private fun releasePlayer() {
        mPlayerView.onPause()
        mPlayer?.release()
        mPlayer = null
    }

    private fun initializeMyPlayer(){
        if (Build.VERSION.SDK_INT > 23)
            initializePlayer()
        else {
            CommonUtils.printToast(this, getString(R.string.error_vrvideo))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        initializeMyPlayer()
    }

    override fun onResume() {
        super.onResume()
        initializeMyPlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= 23) {
            releasePlayer()
        }
        finish()
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            releasePlayer()
        }
    }
}