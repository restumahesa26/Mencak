package com.example.mencak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.R
import com.example.mencak.model.CommentModel
import com.example.mencak.model.FoodModel
import com.example.mencak.model.TagModel

class CommentPostAdapter(private val listComment: ArrayList<CommentModel>) : RecyclerView.Adapter<CommentPostAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvNamePostComment)
        var comment: TextView = itemView.findViewById(R.id.tvDetailPostComment)
        var image: ImageView = itemView.findViewById(R.id.ivPostUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_post, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, comment, image) = listComment[position]
        holder.name?.text = name
        holder.comment?.text = comment
        Glide.with(holder.image.context)
            .load(image)
            .circleCrop()
            .into(holder.image)
    }

    override fun getItemCount(): Int = listComment.size
}