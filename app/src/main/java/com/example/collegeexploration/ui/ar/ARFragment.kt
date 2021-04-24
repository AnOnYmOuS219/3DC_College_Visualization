package com.example.collegeexploration.ui.ar

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.collegeexploration.R
import com.example.collegeexploration.utils.CommonUtils
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


class ARFragment : Fragment(R.layout.fragment_a_r) {

    companion object{
        val TAG: String = ARFragment.javaClass.simpleName
    }

    private lateinit var mArCam: ArFragment
    private var click: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mArCam = childFragmentManager.findFragmentById(R.id.arCameraArea) as ArFragment
        mArCam.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
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
                        CommonUtils.printToast(context, "Something went wrong ..")
                        return@exceptionally null
                    }
            }
        }
    }

    private fun addModel(anchor: Anchor, modelRenderable: ModelRenderable) {

        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(mArCam.arSceneView.scene)
        val transformableNode = TransformableNode(mArCam.transformationSystem)
        transformableNode.setParent(anchorNode)
        transformableNode.renderable = modelRenderable
        transformableNode.select()
    }
}