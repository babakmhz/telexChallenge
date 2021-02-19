package com.android.telexchallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.telexchallenge.R
import com.android.telexchallenge.data.network.Item
import com.android.telexchallenge.databinding.ItemBaseBinding
import com.android.telexchallenge.utils.AppLogger
import com.android.telexchallenge.utils.AppUtils

class MainRecyclerAdapter(
        private val context: Context,
        private var topics: ArrayList<Item>,
) : RecyclerView.Adapter<MainRecyclerAdapter.viewHolder>() {

    private val childAdapters = arrayListOf<HashMap<Item, SubTopicAdapter>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val templateBinding =
                ItemBaseBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(
                context,
                templateBinding,
                templateBinding.root,
        )
    }

    override fun getItemCount(): Int {
        return this.topics.size
    }

    fun setTopicData(data: ArrayList<Item>) {
        this.topics.clear()
        this.topics.addAll(data)
        notifyDataSetChanged()
    }

    fun getCurrentData(): ArrayList<Item> {
        return this.topics
    }

    fun setAdapters(adapter: Map<Item, SubTopicAdapter>) {
        this.childAdapters.add(adapter as HashMap<Item, SubTopicAdapter>)
        notifyItemChanged(getPositionForItem(adapter.keys.last()))
    }

    private fun getPositionForItem(item: Item): Int {
        return this.topics.indexOf(item)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = topics[position]
        try {
            if (childAdapters.isNotEmpty() && childAdapters[position].containsKey(item))
                holder.bind(item, childAdapters[position][item])
            else
                holder.bind(item, null)
        } catch (e: Exception) {
            AppLogger.e(e.toString())
        }
    }

    class viewHolder(
            private val context: Context,
            private val itemBaseBinding: ItemBaseBinding,
            itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val topicRecycler: RecyclerView = itemView.findViewById(R.id.recycler_topic)

        fun bind(topic: Item, adapter: SubTopicAdapter?) {

            itemBaseBinding.repo = topic
            itemBaseBinding.executePendingBindings()

            val orientation = if (AppUtils.isVerticalList(topic.viewType)) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
            topicRecycler.layoutManager = LinearLayoutManager(context, orientation, false)
            if (adapter != null)
                topicRecycler.adapter = adapter

        }
    }

}