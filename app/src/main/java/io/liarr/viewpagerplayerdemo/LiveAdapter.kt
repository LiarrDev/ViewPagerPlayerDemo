package io.liarr.viewpagerplayerdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.liarr.viewpagerplayerdemo.databinding.ItemLiveCoverBinding

class LiveAdapter(private val liveList: List<Live>) : RecyclerView.Adapter<LiveAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemLiveCoverBinding) : RecyclerView.ViewHolder(binding.root) {
        val liveCover: ImageView = binding.liveCover
        val watchingNum: TextView = binding.watchingNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLiveCoverBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val liveItem = liveList[position]
        holder.liveCover.setImageResource(liveItem.coverResId)
        holder.watchingNum.text = liveItem.watchingNum.toString()
    }

    override fun getItemCount() = liveList.size
}