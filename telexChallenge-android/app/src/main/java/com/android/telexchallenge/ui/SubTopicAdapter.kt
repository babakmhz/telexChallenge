package com.android.telexchallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.telexchallenge.R
import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.databinding.ItemSubTopicBinding
import com.android.telexchallenge.utils.AppLogger
import com.bumptech.glide.Glide

class SubTopicAdapter(
        private val context: Context,
        private var data: ArrayList<SubTopic>,
) : RecyclerView.Adapter<SubTopicAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val subTopicBinding =
                ItemSubTopicBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(
                context,
                subTopicBinding,
                subTopicBinding.root,
        )
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(data[position])
    }

    class viewHolder(
            private val context: Context,
            private val subTopicBinding: ItemSubTopicBinding,
            itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {


        private val imageView: ImageView = itemView.findViewById(R.id.image_topic)
        fun bind(topic: SubTopic) {
            subTopicBinding.repo = topic
            subTopicBinding.executePendingBindings()
            AppLogger.i("loading image ${topic.image}")

            Glide.with(itemView).load(topic.image)
                    .into(imageView)

        }
    }

}