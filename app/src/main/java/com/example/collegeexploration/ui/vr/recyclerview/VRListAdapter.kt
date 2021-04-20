package com.example.collegeexploration.ui.vr.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeexploration.R

class VRListAdapter : RecyclerView.Adapter<VRListAdapter.VRViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VRViewHolder {
        return VRViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VRViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 0
    }

    class VRViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mTextViewTitle : TextView = itemView.findViewById(R.id.tv_title)

        fun bind() {
            mTextViewTitle.text = "Random text"
        }

        companion object {
            fun create(parent: ViewGroup): VRViewHolder {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vr_list_item, parent, false)
                return VRViewHolder(view)
            }
        }
    }
}