package com.example.mencak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.R
import com.example.mencak.model.FoodModel

class HistoryFoodAdapter(private val listFood: ArrayList<FoodModel>) : RecyclerView.Adapter<HistoryFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: FoodModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameFood: TextView = itemView.findViewById(R.id.tv_food_name_history)
        var imageFood: ImageView = itemView.findViewById(R.id.ivPoster)
        var button: AppCompatButton = itemView.findViewById(R.id.btnReviewFood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history_food, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, image) = listFood[position]
        holder.nameFood.text = name
        Glide.with(holder.imageFood.context)
            .load(image)
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(holder.imageFood)

        holder.button.setOnClickListener {
            onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listFood.size
}