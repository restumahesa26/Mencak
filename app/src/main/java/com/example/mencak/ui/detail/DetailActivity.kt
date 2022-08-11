package com.example.mencak.ui.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.adapter.PostAdapter
import com.example.mencak.adapter.RelatedFoodAdapter
import com.example.mencak.databinding.ActivityDetailBinding
import com.example.mencak.model.FoodModel
import com.example.mencak.model.PostModel
import com.example.mencak.model.api.ApiConfig
import com.example.mencak.model.response.MencakResponse
import com.example.mencak.ui.createpost.CreatePostActivity
import com.example.mencak.ui.detailpost.DetailPostActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var listFood = ArrayList<FoodModel>()
    private var listPost = ArrayList<PostModel>()
    private lateinit var rcRelatedFood: RecyclerView
    private lateinit var rcPost: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val detailId = intent.getStringExtra("ID")
//        detailFood(detailId.toString())

        val data = intent.getParcelableExtra<FoodModel>("DATA")

        binding.tvDetailFoodName.text = data?.name
        binding.tvDetailFoodCityValue.text = data?.city
        binding.ratingBarDetailFood.rating = data!!.rating
        binding.tvTagDetailFood.text = data.tag
        binding.tvDetailFoodPriceValue.text = data.price
        binding.tvDetailFoodDescriptionValue.text = data.description
        Glide.with(applicationContext)
            .load(data.image)
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.ivDetailFood)

        supportActionBar?.hide()
        setupView()

        rcRelatedFood = binding.rcListFoodHorizontal
        rcRelatedFood.setHasFixedSize(true)

        rcPost = binding.rcListPost
        rcPost.setHasFixedSize(true)

//        initDataDummy()
        initDataDummy2()

//        var adapter = RelatedFoodAdapter(listFood)
//        rcRelatedFood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rcRelatedFood.adapter = adapter
//
//        adapter.setOnItemClickCallback(object : RelatedFoodAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: FoodModel) {
//                val intent = Intent(this@DetailActivity, DetailActivity::class.java)
//                intent.putExtra("DATA", data)
//                startActivity(intent)
//                finish()
//            }
//        })

        var adapter2 = PostAdapter(listPost)
        rcPost.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcPost.adapter = adapter2

        adapter2.setOnItemClickCallback(object : PostAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PostModel) {
                val intent = Intent(this@DetailActivity, DetailPostActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })

//        val data = intent.getParcelableExtra<FoodModel>("DATA")

//        binding.tvDetailFoodName.text = data?.name.toString()
//        binding.tvDetailFoodCityValue.text = data?.city.toString()
//        binding.tvDetailFoodDescriptionValue.text = data?.description.toString()
//        binding.ratingBarDetailFood.rating = data?.rating!!.toFloat()

        binding.btnCreatePost.setOnClickListener {
            val intent = Intent(this@DetailActivity, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
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

    private fun detailFood(id: String) {
        val client = ApiConfig.getApiService().getDetailFood(id)
        client.enqueue(object : Callback<MencakResponse.FoodResponse> {
            override fun onResponse(
                call: Call<MencakResponse.FoodResponse>,
                response: Response<MencakResponse.FoodResponse>
            ) {
                if (response.isSuccessful) {
                    binding.tvDetailFoodName.text = response.body()?.namaMakanan
                    Glide.with(binding.ivDetailFood.context)
                        .load(response.body()?.fotoMakanan)
                        .into(binding.ivDetailFood)
                    binding.tvDetailFoodPriceValue.text = response.body()?.hargaTerendah
                    binding.tvDetailFoodCityValue.text = response.body()?.tag
                    binding.tvDetailFoodDescriptionValue.text = response.body()?.deskripsiMakanan
                }
            }

            override fun onFailure(call: Call<MencakResponse.FoodResponse>, t: Throwable) {
                Log.d("DetailAccActivity", "onFailure: ${t.message.toString()}")
            }
        })
    }
}