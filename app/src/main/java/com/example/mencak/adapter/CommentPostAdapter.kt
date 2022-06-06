package com.example.mencak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.model.CommentModel
import com.example.mencak.model.FoodModel
import com.example.mencak.model.TagModel

class CommentPostAdapter(private val listComment: ArrayList<CommentModel>) : RecyclerView.Adapter<CommentPostAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvNamePostComment)
        var comment: TextView = itemView.findViewById(R.id.tvDetailPostComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_post, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, comment) = listComment[position]
        holder.name?.text = name
        holder.comment?.text = comment
    }

    override fun getItemCount(): Int = listComment.size
}