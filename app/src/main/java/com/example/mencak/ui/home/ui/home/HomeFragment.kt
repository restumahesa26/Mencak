package com.example.mencak.ui.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mencak.R
import com.example.mencak.adapter.*
import com.example.mencak.databinding.FragmentHomeBinding
import com.example.mencak.model.FoodModel
import com.example.mencak.model.PostModel
import com.example.mencak.model.TagModel
import com.example.mencak.model.response.MencakResponse
import com.example.mencak.ui.createpost.CreatePostActivity
import com.example.mencak.ui.detail.DetailActivity
import com.example.mencak.ui.detailpost.DetailPostActivity

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

//        initDataDummy()
        initDataDummy2()
//        initDataDummy3()

//        val homeViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        )[HomeViewModel::class.java]
//        homeViewModel.listFood.observe(viewLifecycleOwner) {
//            showFood(it)
//        }

        listFood.addAll(listFoods)

        val adapter = FoodYourLocationAdapter(listFood)
        rcFoodLocation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcFoodLocation.adapter = adapter

        adapter.setOnItemClickCallback(object: FoodYourLocationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FoodModel) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })

//        var adapter = FoodYourLocationAdapter(listFood)
//        rcFoodLocation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        rcFoodLocation.adapter = adapter
//
//        adapter.setOnItemClickCallback(object : FoodYourLocationAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: FoodModel) {
//                val intent = Intent(requireContext(), DetailActivity::class.java)
//                intent.putExtra("DATA", data)
//                startActivity(intent)
//            }
//        })

        var adapter2 = PopulerTagAdapter(listTag)
        rcPopulerTag.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcPopulerTag.adapter = adapter2

        adapter2.setOnItemClickCallback(object : PopulerTagAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TagModel) {
                showSelectedPopulerTag(data)
            }
        })

        listPost.addAll(listPosts)

        var adapter3 = PostAdapter(listPost)
        rcPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcPost.adapter = adapter3

        adapter3.setOnItemClickCallback(object : PostAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PostModel) {
                val intent = Intent(requireContext(), DetailPostActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })

        binding.linearLayoutFood.setOnClickListener {
            Toast.makeText(requireContext(), "Kamu Mengklik Tombol Scan Food", Toast.LENGTH_SHORT).show()
        }

        binding.btnCreatePost.setOnClickListener {
            val intent = Intent(requireContext(), CreatePostActivity::class.java)
            startActivity(intent)
        }
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

    private val listPosts: ArrayList<PostModel>
        get() {
            val dataName = resources.getStringArray(R.array.data_post_name)
            val dataProfil = resources.getStringArray(R.array.data_post_photo)
            val dataPhoto = resources.getStringArray(R.array.data_post_photo)
            val dataTitle = resources.getStringArray(R.array.data_post_title)
            val dataComment = resources.getStringArray(R.array.data_post_comment)
            val dataTag = resources.getStringArray(R.array.data_post_tag)
            val listPost = ArrayList<PostModel>()
            for (i in dataName.indices) {
                val post = PostModel(dataName[i], dataProfil[i], dataTitle[i], dataPhoto[i], dataComment[i].toInt(), dataTag[i])
                listPost.add(post)
            }
            return listPost
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
        listPost.add(PostModel("Mufti Restu Mahesa", "", "Ketoprak Enak", "", 100, "ketoprak"))
        listPost.add(PostModel("Adde Nanda C. Putra", "", "Batagor Enak", "", 30, "batagor"))
        listPost.add(PostModel("M. Daffa Alfajri", "", "Batagor Enak", "", 200, "batagor"))
        listPost.add(PostModel("Khoirus Fauzi", "", "Bakso Enak", "", 50, "bakso"))
        listPost.add(PostModel("Maria Misela", "", "Ikan Bakar Enak", "", 80, "ikanbakar"))
        listPost.add(PostModel("Aditya", "", "Gado-gado Enak", "", 25, "gadogado"))
        listPost.add(PostModel("Andrei Jonior", "", "Ayam Penyet Enak", "", 10, "ayampenyet"))
        listPost.add(PostModel("Rolin Sanjaya", "", "Lumpia Goreng Enak", "", 65, "lumpia"))
        listPost.add(PostModel("Rizki Gusmanto", "", "Gorengan Enak", "", 35, "gorengan"))
        listPost.add(PostModel("Mufti Restu Mahesa", "", "Ketoprak Enak", "", 100, "ketoprak"))
        listPost.add(PostModel("Adde Nanda C. Putra", "", "Batagor Enak", "", 30, "batagor"))
        listPost.add(PostModel("M. Daffa Alfajri", "", "Batagor Enak", "", 200, "batagor"))
        listPost.add(PostModel("Khoirus Fauzi", "", "Bakso Enak", "", 50, "bakso"))
        listPost.add(PostModel("Maria Misela", "", "Ikan Bakar Enak", "", 80, "ikanbakar"))
        listPost.add(PostModel("Aditya", "", "Gado-gado Enak", "", 25, "gadogado"))
        listPost.add(PostModel("Andrei Jonior", "", "Ayam Penyet Enak", "", 10, "ayampenyet"))
        listPost.add(PostModel("Rolin Sanjaya", "", "Lumpia Goreng Enak", "", 65, "lumpia"))
        listPost.add(PostModel("Rizki Gusmanto", "", "Gorengan Enak", "", 35, "gorengan"))
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

    private fun showFood(user: ArrayList<MencakResponse.FoodResponse>) {
        binding.rcListFoodHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listGithubAccAdapter = RelatedFoodAdapter(user)
        binding.rcListFoodHorizontal.adapter = listGithubAccAdapter
        listGithubAccAdapter.setOnItemClickCallback(object :
            RelatedFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MencakResponse.FoodResponse) {
                val detailAcc = Intent(requireContext(), DetailActivity::class.java)
                detailAcc.putExtra("ID", data.id)
                startActivity(detailAcc)
            }
        })
    }
}