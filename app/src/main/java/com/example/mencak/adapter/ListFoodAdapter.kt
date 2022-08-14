package com.example.mencak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mencak.R
import com.example.mencak.model.FoodModel
import com.example.mencak.model.response.MencakResponse

class ListFoodAdapter(private var listFood: ArrayList<FoodModel>) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
//        fun onItemClicked(data: MencakResponse.FoodResponse)
        fun onItemClicked(data: FoodModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<FoodModel>) {
        // below line is to add our filtered
        // list in our course array list.
        listFood = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameFood: TextView = itemView.findViewById(R.id.tvFoodListName)
        var image: ImageView = itemView.findViewById(R.id.ivPoster)
        var cityFood: TextView = itemView.findViewById(R.id.tvFoodListCity)
        var ratingFood: RatingBar = itemView.findViewById(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_food, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, image, rating, city) = listFood[position]
        holder.nameFood.text = name
        holder.cityFood.text = city
        holder.ratingFood.setRating(rating)
        Glide.with(holder.image.context)
            .load(image)
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(holder.image)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])
        }
//        holder.nameFood.text = listFood[position].namaMakanan
//        Glide.with(holder.image.context)
//            .load(listFood[position].fotoMakanan)
//            .transforms(CenterCrop(), RoundedCorners(16))
//            .into(holder.image)
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])
//        }
    }

    override fun getItemCount(): Int = listFood.size
}