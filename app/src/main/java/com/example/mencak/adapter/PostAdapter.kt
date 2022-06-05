package com.example.mencak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.model.PostModel

class PostAdapter(private val listPost: ArrayList<PostModel>) : RecyclerView.Adapter<PostAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: PostModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profil: ImageView = itemView.findViewById(R.id.ivPostUser)
        var name: TextView = itemView.findViewById(R.id.tvNamePost)
        var title: TextView = itemView.findViewById(R.id.tvTitlePost)
        var image: ImageView = itemView.findViewById(R.id.ivPostImage)
        var comment: TextView = itemView.findViewById(R.id.tvComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, profil, title, image, comment) = listPost[position]
        holder.name?.text = name
        holder.title?.text = title
        holder.comment?.text = "$comment comments"

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPost[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listPost.size
}