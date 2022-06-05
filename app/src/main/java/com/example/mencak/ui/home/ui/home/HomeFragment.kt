package com.example.mencak.ui.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.adapter.FoodYourLocationAdapter
import com.example.mencak.adapter.PopulerTagAdapter
import com.example.mencak.adapter.PostAdapter
import com.example.mencak.databinding.FragmentHomeBinding
import com.example.mencak.model.FoodModel
import com.example.mencak.model.PostModel
import com.example.mencak.model.TagModel
import com.example.mencak.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var listFood = ArrayList<FoodModel>()
    private var listTag = ArrayList<TagModel>()
    private var listPost = ArrayList<PostModel>()
    private lateinit var rcFoodLocation: RecyclerView
    private lateinit var rcPopulerTag: RecyclerView
    private lateinit var rcPost: RecyclerView

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

        rcPost = binding.rcListPost
        rcPost.setHasFixedSize(true)

        initDataDummy()
        initDataDummy2()
        initDataDummy3()

        var adapter = FoodYourLocationAdapter(listFood)
        rcFoodLocation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcFoodLocation.adapter = adapter

        adapter.setOnItemClickCallback(object : FoodYourLocationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                showSelectedFoodYourLocation(data)
            }
        })

        var adapter2 = PopulerTagAdapter(listTag)
        rcPopulerTag.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcPopulerTag.adapter = adapter2

        adapter2.setOnItemClickCallback(object : PopulerTagAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TagModel) {
                showSelectedPopulerTag(data)
            }
        })

        var adapter3 = PostAdapter(listPost)
        rcPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcPost.adapter = adapter3

        adapter3.setOnItemClickCallback(object : PostAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PostModel) {
                showSelectedPost(data)
            }
        })

        binding.linearLayoutFood.setOnClickListener {
            Toast.makeText(requireContext(), "Kamu Mengklik Tombol Scan Food", Toast.LENGTH_SHORT).show()
        }

        binding.btnCreatePost.setOnClickListener {
            Toast.makeText(requireContext(), "Kamu Mengklik Tombol Create Post", Toast.LENGTH_SHORT).show()
        }
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

    fun initDataDummy3() {
        listPost = ArrayList()
        listPost.add(PostModel("Mufti Restu Mahesa", "", "Ketoprak Enak", "", 100))
        listPost.add(PostModel("Adde Nanda C. Putra", "", "Batagor Enak", "", 30))
        listPost.add(PostModel("M. Daffa Alfajri", "", "Batagor Enak", "", 200))
        listPost.add(PostModel("Khoirus Fauzi", "", "Bakso Enak", "", 50))
        listPost.add(PostModel("Maria Misela", "", "Ikan Bakar Enak", "", 80))
        listPost.add(PostModel("Aditya", "", "Gado-gado Enak", "", 25))
        listPost.add(PostModel("Andrei Jonior", "", "Ayam Penyet Enak", "", 10))
        listPost.add(PostModel("Rolin Sanjaya", "", "Lumpia Goreng Enak", "", 65))
        listPost.add(PostModel("Rizki Gusmanto", "", "Gorengan Enak", "", 35))
        listPost.add(PostModel("Mufti Restu Mahesa", "", "Ketoprak Enak", "", 100))
        listPost.add(PostModel("Adde Nanda C. Putra", "", "Batagor Enak", "", 30))
        listPost.add(PostModel("M. Daffa Alfajri", "", "Batagor Enak", "", 200))
        listPost.add(PostModel("Khoirus Fauzi", "", "Bakso Enak", "", 50))
        listPost.add(PostModel("Maria Misela", "", "Ikan Bakar Enak", "", 80))
        listPost.add(PostModel("Aditya", "", "Gado-gado Enak", "", 25))
        listPost.add(PostModel("Andrei Jonior", "", "Ayam Penyet Enak", "", 10))
        listPost.add(PostModel("Rolin Sanjaya", "", "Lumpia Goreng Enak", "", 65))
        listPost.add(PostModel("Rizki Gusmanto", "", "Gorengan Enak", "", 35))
    }

    private fun showSelectedFoodYourLocation(food: FoodModel) {
        Toast.makeText(requireContext(), "Kamu Memilih " + food.name, Toast.LENGTH_SHORT).show()
    }

    private fun showSelectedPopulerTag(tag: TagModel) {
        Toast.makeText(requireContext(), "Kamu Memilih " + tag.name, Toast.LENGTH_SHORT).show()
    }

    private fun showSelectedPost(post: PostModel) {
        Toast.makeText(requireContext(), "Kamu Memilih " + post.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}