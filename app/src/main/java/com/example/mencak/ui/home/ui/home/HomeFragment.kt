package com.example.mencak.ui.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.adapter.FoodYourLocationAdapter
import com.example.mencak.adapter.PopulerTagAdapter
import com.example.mencak.databinding.FragmentHomeBinding
import com.example.mencak.model.FoodModel
import com.example.mencak.model.TagModel
import com.example.mencak.ui.login.LoginActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var listFood = ArrayList<FoodModel>()
    private var listTag = ArrayList<TagModel>()
    private lateinit var rcFoodLocation: RecyclerView
    private lateinit var rcPopulerTag: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcFoodLocation = binding.rcListFoodHorizontal
        rcFoodLocation.setHasFixedSize(true)

        rcPopulerTag = binding.rcListPopulerTag
        rcPopulerTag.setHasFixedSize(true)

        initDataDummy()
        initDataDummy2()

        var adapter = FoodYourLocationAdapter(listFood)
        rcFoodLocation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcFoodLocation.adapter = adapter

        var adapter2 = PopulerTagAdapter(listTag)
        rcPopulerTag.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcPopulerTag.adapter = adapter2
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

    fun initDataDummy2() {
        listTag = ArrayList()
        listTag.add(TagModel("tradisional"))
        listTag.add(TagModel("satepadang"))
        listTag.add(TagModel("rendang"))
        listTag.add(TagModel("jakartafood"))
        listTag.add(TagModel("gudegjogja"))
        listTag.add(TagModel("balipork"))
        listTag.add(TagModel("meatball"))
        listTag.add(TagModel("healthyfood"))
        listTag.add(TagModel("indomie"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}