package com.example.collegeexploration.ui.vr.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R
import com.example.collegeexploration.data.MediaItemFireBase
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily

class MediaFirebaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // init views
    private val mTextViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
    private val mTextViewDescription: TextView = itemView.findViewById(R.id.text_view_description)
    private val mImageViewThumbnail: ShapeableImageView = itemView.findViewById(R.id.image_view_thumbnail)

    // binds data with views
    fun bind(mediaItem: MediaItemFireBase) {
        mTextViewTitle.text = mediaItem.title
        mTextViewDescription.text = mediaItem.description
        mImageViewThumbnail.shapeAppearanceModel = mImageViewThumbnail.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 35F)
            .build()
//        Picasso.get().load(mediaItem.image).into(mImageViewThumbnail)
//        mImageViewThumbnail.setImageResource(mediaItem.mediaId)
        if(mImageViewThumbnail.drawable == null)
            mImageViewThumbnail.visibility = View.GONE
    }

    companion object {
        // static function to create a VRViewHolder object
        fun create(parent: ViewGroup): VRListAdapter.VRViewHolder {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
            return VRListAdapter.VRViewHolder(view)
        }
    }
}