package com.example.collegeexploration.ui.ar

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.example.collegeexploration.R
import com.example.collegeexploration.utils.CommonUtils
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

/**
 * Augmented Reality Fragment
 */
class ARFragment : Fragment(R.layout.fragment_a_r) {

    private lateinit var mArCam: ArFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var click = 0
        mArCam = childFragmentManager.findFragmentById(R.id.arCameraArea) as ArFragment

        mArCam.setOnTapArPlaneListener { hitResult, _, _ ->
            click++
            if (click == 1) {
                val anchor: Anchor = hitResult.createAnchor()
                ModelRenderable.builder()
                    .setSource(activity, R.raw.pine)
                    .setIsFilamentGltf(true)
                    .build()
                    .thenAccept {
                            modelRenderable -> addModel(anchor, modelRenderable)
                    }
                    .exceptionally{
                        CommonUtils.printToast(context, getString(R.string.error_ar))
                        return@exceptionally null
                    }
            }
        }
    }

    private fun addModel(anchor: Anchor, model: ModelRenderable) {

        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(mArCam.arSceneView.scene)
        val transformableNode = TransformableNode(mArCam.transformationSystem)
        transformableNode.setParent(anchorNode)
        transformableNode.renderable = model
        transformableNode.select()
    }

    companion object{
        val LOG_TAG: String = ARFragment.javaClass.simpleName
    }
}