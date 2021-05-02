package com.example.collegeexploration.ui.vrvideo

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeexploration.R
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource

class VRVideoActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null
    private lateinit var player_view: PlayerView
    private var mIntentMediaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vr_video)

        player_view = findViewById(R.id.exo_player_vr)

        (player_view.videoSurfaceView as SphericalGLSurfaceView)
            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)

        mIntentMediaId = intent.getIntExtra("mediaId", -1)
    }


    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Build.VERSION.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
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

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this, "sakiv-vr")
        // Create a media source using the supplied URI
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(uri))
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()

//        val uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-1/360/congo.mp4")
        val uri = RawResourceDataSource.buildRawResourceUri(mIntentMediaId)
        val mediaSource = buildMediaSource(uri)
        player?.setMediaSource(mediaSource)

        player_view.player = player
        player_view.onResume()
    }

    private fun releasePlayer() {
        player_view.onPause()
        player?.release()
        player = null
    }
}