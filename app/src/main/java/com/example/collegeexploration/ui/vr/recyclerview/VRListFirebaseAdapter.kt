package com.example.collegeexploration.ui.vr.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R
import com.example.collegeexploration.data.MediaItemFireBase
import com.example.collegeexploration.events.VREventFirebase
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import org.greenrobot.eventbus.EventBus

class VRListFirebaseAdapter(diffCallback: DiffUtil.ItemCallback<MediaItemFireBase>) :
    ListAdapter<MediaItemFireBase, VRListFirebaseAdapter.VRFirebaseViewHolder>(diffCallback) {

    /**
     * ListAdapter's DiffUtil ItemCallback class
     */
    class MediaItemListDiff : DiffUtil.ItemCallback<MediaItemFireBase>() {
        override fun areItemsTheSame(oldItem: MediaItemFireBase, newItem: MediaItemFireBase): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MediaItemFireBase, newItem: MediaItemFireBase): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VRFirebaseViewHolder {
        return VRFirebaseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VRFirebaseViewHolder, position: Int) {
        val mediaItem: MediaItemFireBase = getItem(position)
        holder.bind(mediaItem)
        holder.itemView.setOnClickListener { _ ->
            val vrEvent = VREventFirebase(mediaItem)
            // post an EventBus event
            EventBus.getDefault().post(vrEvent)
        }
    }

    fun getMediaItemAt(position: Int): MediaItemFireBase {
        return getItem(position)
    }

    companion object {
        fun createDiffClass(): MediaItemListDiff = MediaItemListDiff()
    }

    /**
     * ViewHolder class
     */
    class VRFirebaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
//            mImageViewThumbnail.setImageResource(mediaItem.mediaId)
            Picasso.get().load(mediaItem.url).into(mImageViewThumbnail)
            if(mediaItem.url == null)
                mImageViewThumbnail.visibility = View.GONE
//            if(mImageViewThumbnail.drawable == null)
//                mImageViewThumbnail.visibility = View.GONE
        }

        companion object {
            // static function to create a VRViewHolder object
            fun create(parent: ViewGroup): VRFirebaseViewHolder {
                var view: View = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
                return VRFirebaseViewHolder(view)
            }
        }
    }
}