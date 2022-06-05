package com.example.mencak.ui.home.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.adapter.HistoryFoodAdapter
import com.example.mencak.adapter.ListFoodAdapter
import com.example.mencak.databinding.FragmentFoodBinding
import com.example.mencak.model.FoodModel

//import com.example.mencak.ui.home.databinding.FragmentNotificationsBinding

class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var rcListFood: RecyclerView
    private var listFood = ArrayList<FoodModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcListFood = binding.rcListFood
        rcListFood.setHasFixedSize(true)

        initDataDummy()

        var adapter = ListFoodAdapter(listFood)
        rcListFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcListFood.adapter = adapter

        adapter.setOnItemClickCallback(object: ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                Toast.makeText(requireContext(), "Kamu Memilih " + data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initDataDummy() {
        listFood = ArrayList()
        listFood.add(FoodModel("Ketoprak", "", 4f, "Jakarta"))
        listFood.add(FoodModel("Sate Padang", "", 3.5f, "Padang"))
        listFood.add(FoodModel("Sate Madura", "", 2.3f, "Madura"))
        listFood.add(FoodModel("Bakso", "", 3.4f, "Solo"))
        listFood.add(FoodModel("Mie Ayam", "", 1.5f, "Solo"))
        listFood.add(FoodModel("Ayam Geprek", "", 4.5f, "Indonesia"))
        listFood.add(FoodModel("Ikan Bakar", "", 5f, "Indonesia"))
        listFood.add(FoodModel("Gado-Gado", "", 4.2f, "Jawa Barat"))
        listFood.add(FoodModel("Bubur Ayam", "", 4.7f, "Jakarta"))
        listFood.add(FoodModel("Ketoprak", "", 4f, "Jakarta"))
        listFood.add(FoodModel("Sate Padang", "", 3.5f, "Padang"))
        listFood.add(FoodModel("Sate Madura", "", 2.3f, "Madura"))
        listFood.add(FoodModel("Bakso", "", 3.4f, "Solo"))
        listFood.add(FoodModel("Mie Ayam", "", 1.5f, "Solo"))
        listFood.add(FoodModel("Ayam Geprek", "", 4.5f, "Indonesia"))
        listFood.add(FoodModel("Ikan Bakar", "", 5f, "Indonesia"))
        listFood.add(FoodModel("Gado-Gado", "", 4.2f, "Jawa Barat"))
        listFood.add(FoodModel("Bubur Ayam", "", 4.7f, "Jakarta"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}