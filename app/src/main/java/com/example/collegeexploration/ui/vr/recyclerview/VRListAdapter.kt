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
import org.greenrobot.eventbus.EventBus

class VRListAdapter(diffCallback: DiffUtil.ItemCallback<MediaItem>) :
    ListAdapter<MediaItem, VRListAdapter.VRViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VRViewHolder {
        return VRViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VRViewHolder, position: Int) {
        val mediaItem: MediaItem = getItem(position)
        holder.bind(mediaItem)
        holder.itemView.setOnClickListener { v ->
            val vrEvent: VREvent = VREvent(mediaItem)
            EventBus.getDefault().post(vrEvent)
        }
    }

    fun getMediaItemAt(position: Int): MediaItem {
        return getItem(position)
    }

    class MediaItemListDiff : DiffUtil.ItemCallback<MediaItem>() {
        override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }
    }

    companion object {
        fun createDiffClass(): MediaItemListDiff = MediaItemListDiff()
    }

    class VRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        private val mTextViewDescription: TextView =
            itemView.findViewById(R.id.text_view_description)
        private val mImageViewThumbnail: ImageView =
            itemView.findViewById(R.id.image_view_thumbnail)

        fun bind(mediaItem: MediaItem) {
            mTextViewTitle.text = mediaItem.mediaTitle
            mTextViewDescription.text = mediaItem.mediaDescription
            mImageViewThumbnail.setImageResource(mediaItem.mediaId)
        }

        companion object {
            fun create(parent: ViewGroup): VRViewHolder {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.media_item, parent, false)
                return VRViewHolder(view)
            }
        }
    }
}