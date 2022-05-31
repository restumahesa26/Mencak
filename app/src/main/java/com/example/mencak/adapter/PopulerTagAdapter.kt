package com.example.mencak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.model.FoodModel
import com.example.mencak.model.TagModel

class PopulerTagAdapter(private val listTag: ArrayList<TagModel>) : RecyclerView.Adapter<PopulerTagAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: TagModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTag: TextView = itemView.findViewById(R.id.tv_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_populer_tag, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name) = listTag[position]
        holder.nameTag?.text = "#$name "

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTag[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listTag.size
}