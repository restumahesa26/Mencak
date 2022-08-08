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
import com.example.mencak.model.FoodModel
import com.example.mencak.model.response.MencakResponse

class RelatedFoodAdapter(private val listFood: ArrayList<MencakResponse.FoodResponse>) : RecyclerView.Adapter<RelatedFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: MencakResponse.FoodResponse)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.iv_food_image)
        var nameFood: TextView = itemView.findViewById(R.id.tv_food_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_food_your_location_horizontal, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (name, image, rating, city) = listFood[position]
//        holder.nameFood?.text = name.toString()
//
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])
//        }
        holder.nameFood.text = listFood[position].namaMakanan
        Glide.with(holder.image.context)
            .load(listFood[position].fotoMakanan)
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(holder.image)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFood[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listFood.size
}