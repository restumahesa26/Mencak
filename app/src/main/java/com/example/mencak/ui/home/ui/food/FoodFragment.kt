package com.example.mencak.ui.home.ui.food

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.adapter.ListFoodAdapter
import com.example.mencak.adapter.RelatedFoodAdapter
import com.example.mencak.databinding.FragmentFoodBinding
import com.example.mencak.model.FoodModel
import com.example.mencak.model.response.MencakResponse
import com.example.mencak.ui.detail.DetailActivity
import com.example.mencak.ui.home.ui.home.HomeViewModel

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

//        initDataDummy()

//        val homeViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        )[HomeViewModel::class.java]
//        homeViewModel.listFood.observe(viewLifecycleOwner) {
//            showFood(it)
//        }
        listFood.addAll(listFoods)

        val adapter = ListFoodAdapter(listFood)
        rcListFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcListFood.adapter = adapter

        adapter.setOnItemClickCallback(object: ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
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
//        listFood.add(FoodModel("Ketoprak", "", 4f, "Jakarta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Sate Padang", "", 3.5f, "Padang", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Sate Madura", "", 2.3f, "Madura", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Bakso", "", 3.4f, "Solo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Mie Ayam", "", 1.5f, "Solo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Ayam Geprek", "", 4.5f, "Indonesia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Ikan Bakar", "", 5f, "Indonesia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Gado-Gado", "", 4.2f, "Jawa Barat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Bubur Ayam", "", 4.7f, "Jakarta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Ketoprak", "", 4f, "Jakarta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Sate Padang", "", 3.5f, "Padang", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Sate Madura", "", 2.3f, "Madura", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Bakso", "", 3.4f, "Solo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Mie Ayam", "", 1.5f, "Solo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Ayam Geprek", "", 4.5f, "Indonesia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Ikan Bakar", "", 5f, "Indonesia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Gado-Gado", "", 4.2f, "Jawa Barat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//        listFood.add(FoodModel("Bubur Ayam", "", 4.7f, "Jakarta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas diam in arcu cursus euismod. Morbi non arcu risus quis varius quam quisque id. Mauris ultrices eros in cursus. Dictum sit amet justo donec. Ultricies integer quis auctor elit. Integer vitae justo eget magna fermentum iaculis eu non. Convallis tellus id interdum velit laoreet id donec. Arcu odio ut sem nulla pharetra diam. Et magnis dis parturient montes nascetur. "))
//    }

//    private fun showFood(user: ArrayList<MencakResponse.FoodResponse>) {
//        binding.rcListFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        val listGithubAccAdapter = ListFoodAdapter(user)
//        binding.rcListFood.adapter = listGithubAccAdapter
//        listGithubAccAdapter.setOnItemClickCallback(object :
//            ListFoodAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: MencakResponse.FoodResponse) {
//                val detailAcc = Intent(requireContext(), DetailActivity::class.java)
//                detailAcc.putExtra("ID", data.id)
//                startActivity(detailAcc)
//            }
//        })
//    }

    private fun showFood(user: ArrayList<FoodModel>) {
        binding.rcListFood.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listGithubAccAdapter = ListFoodAdapter(user)
        binding.rcListFood.adapter = listGithubAccAdapter
        listGithubAccAdapter.setOnItemClickCallback(object :
            ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                val detailAcc = Intent(requireContext(), DetailActivity::class.java)
                detailAcc.putExtra("DATA", data)
                startActivity(detailAcc)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}