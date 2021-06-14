package com.example.collegeexploration.ui.vr.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R
import com.example.collegeexploration.data.MediaItem
import com.example.collegeexploration.events.VREvent
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import org.greenrobot.eventbus.EventBus

class VRListAdapter(diffCallback: DiffUtil.ItemCallback<MediaItem>) :
    ListAdapter<MediaItem, VRListAdapter.VRViewHolder>(diffCallback) {

    /**
     * ListAdapter's DiffUtil ItemCallback class
     */
    class MediaItemListDiff : DiffUtil.ItemCallback<MediaItem>() {
        override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VRViewHolder {
        return VRViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VRViewHolder, position: Int) {
        val mediaItem: MediaItem = getItem(position)
        holder.bind(mediaItem)
        holder.itemView.setOnClickListener { _ ->
            val vrEvent: VREvent = VREvent(mediaItem)
            // post an EventBus event
            EventBus.getDefault().post(vrEvent)
        }
    }

    fun getMediaItemAt(position: Int): MediaItem {
        return getItem(position)
    }

    companion object {
        fun createDiffClass(): MediaItemListDiff = MediaItemListDiff()
    }

    /**
     * ViewHolder class
     */
    class VRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // init views
        private val mTextViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        private val mTextViewDescription: TextView = itemView.findViewById(R.id.text_view_description)
        private val mImageViewThumbnail: ShapeableImageView = itemView.findViewById(R.id.image_view_thumbnail)

        // binds data with views
        fun bind(mediaItem: MediaItem) {
            mTextViewTitle.text = mediaItem.mediaTitle
            mTextViewDescription.text = mediaItem.mediaDescription
            mImageViewThumbnail.shapeAppearanceModel = mImageViewThumbnail.shapeAppearanceModel
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 35F)
                .build()
            mImageViewThumbnail.setImageResource(mediaItem.mediaId)
            if(mImageViewThumbnail.drawable == null)
                mImageViewThumbnail.visibility = View.GONE
        }

        companion object {
            // static function to create a VRViewHolder object
            fun create(parent: ViewGroup): VRViewHolder {
                var view: View = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
                return VRViewHolder(view)
            }
        }
    }
}