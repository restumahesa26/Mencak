package com.example.mencak.ui.home.ui.profile.history

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.adapter.HistoryFoodAdapter
import com.example.mencak.adapter.ListFoodAdapter
import com.example.mencak.databinding.FragmentHistoryBinding
import com.example.mencak.model.FoodModel
import com.example.mencak.ui.reviewfood.ReviewFoodActivity

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var rcHistoryFood: RecyclerView
    private var listFood = ArrayList<FoodModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcHistoryFood = binding.rcHistoryFood
        rcHistoryFood.setHasFixedSize(true)

//        initDataDummy()

        listFood.addAll(listFoods)

        val adapter = HistoryFoodAdapter(listFood)
        rcHistoryFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcHistoryFood.adapter = adapter

        adapter.setOnItemClickCallback(object: HistoryFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                val intent = Intent(requireContext(), ReviewFoodActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })
    }

    private val listFoods: ArrayList<FoodModel>
        get() {
            val dataName = resources.getStringArray(R.array.data_food_name)
            val dataDescription = resources.getStringArray(R.array.data_food_description)
            val dataPhoto = resources.getStringArray(R.array.data_food_foto)
            val dataCity = resources.getStringArray(R.array.data_food_city)
            val dataPrice = resources.getStringArray(R.array.data_food_price)
            val dataTag = resources.getStringArray(R.array.data_food_tag)
            val dataRating = resources.getStringArray(R.array.data_food_rating)
            val listFood = ArrayList<FoodModel>()
            for (i in dataName.indices) {
                val food = FoodModel(dataName[i], dataPhoto[i], dataRating[i].toFloat(), dataCity[i], dataDescription[i], dataPrice[i], dataTag[i])
                listFood.add(food)
            }
            return listFood
        }

//    fun initDataDummy() {
//        listFood = ArrayList()
//        listFood.add(FoodModel("Ketoprak", "", 0f, ""))
//        listFood.add(FoodModel("Sate Padang", "", 0f, ""))
//        listFood.add(FoodModel("Sate Madura", "", 0f, ""))
//        listFood.add(FoodModel("Bakso", "", 0f, ""))
//        listFood.add(FoodModel("Mie Ayam", "", 0f, ""))
//        listFood.add(FoodModel("Ayam Geprek", "", 0f, ""))
//        listFood.add(FoodModel("Ikan Bakar", "", 0f, ""))
//        listFood.add(FoodModel("Gado-Gado", "", 0f, ""))
//        listFood.add(FoodModel("Bubur Ayam", "", 0f, ""))
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}