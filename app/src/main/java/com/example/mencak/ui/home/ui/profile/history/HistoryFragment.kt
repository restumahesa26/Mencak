package com.example.mencak.ui.home.ui.profile.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.adapter.HistoryFoodAdapter
import com.example.mencak.adapter.ListFoodAdapter
import com.example.mencak.databinding.FragmentHistoryBinding
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

        adapter.setOnItemClickCallback(object: HistoryFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                Toast.makeText(requireContext(), "Kamu Memilih " + data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initDataDummy() {
        listFood = ArrayList()
        listFood.add(FoodModel("Ketoprak", "", 0f, ""))
        listFood.add(FoodModel("Sate Padang", "", 0f, ""))
        listFood.add(FoodModel("Sate Madura", "", 0f, ""))
        listFood.add(FoodModel("Bakso", "", 0f, ""))
        listFood.add(FoodModel("Mie Ayam", "", 0f, ""))
        listFood.add(FoodModel("Ayam Geprek", "", 0f, ""))
        listFood.add(FoodModel("Ikan Bakar", "", 0f, ""))
        listFood.add(FoodModel("Gado-Gado", "", 0f, ""))
        listFood.add(FoodModel("Bubur Ayam", "", 0f, ""))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}