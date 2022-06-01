package com.example.mencak.ui.home.ui.profile.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.adapter.FoodYourLocationAdapter
import com.example.mencak.databinding.FragmentHistoryBinding
import com.example.mencak.databinding.FragmentHomeBinding
import com.example.mencak.model.FoodModel

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

        initDataDummy()

        var adapter = HistoryFoodAdapter(listFood)
        rcHistoryFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcHistoryFood.adapter = adapter
    }

    fun initDataDummy() {
        listFood = ArrayList()
        listFood.add(FoodModel("Ketoprak", ""))
        listFood.add(FoodModel("Sate Padang", ""))
        listFood.add(FoodModel("Sate Madura", ""))
        listFood.add(FoodModel("Bakso", ""))
        listFood.add(FoodModel("Mie Ayam", ""))
        listFood.add(FoodModel("Ayam Geprek", ""))
        listFood.add(FoodModel("Ikan Bakar", ""))
        listFood.add(FoodModel("Gado-Gado", ""))
        listFood.add(FoodModel("Bubur Ayam", ""))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}