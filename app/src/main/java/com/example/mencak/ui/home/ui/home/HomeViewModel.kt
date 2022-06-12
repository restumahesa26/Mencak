package com.example.mencak.ui.home.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mencak.model.api.ApiConfig
import com.example.mencak.model.response.MencakResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listFood = MutableLiveData<ArrayList<MencakResponse.FoodResponse>>()
    val listFood: LiveData<ArrayList<MencakResponse.FoodResponse>> = _listFood

    val emptyFood = ArrayList<MencakResponse.FoodResponse>()

    init {
        detailFood()
    }

    private fun detailFood() {
        val client = ApiConfig.getApiService().getFood()
        client.enqueue(object : Callback<ArrayList<MencakResponse.FoodResponse>> {
            override fun onResponse(
                call: Call<ArrayList<MencakResponse.FoodResponse>>,
                response: Response<ArrayList<MencakResponse.FoodResponse>>
            ) {
                if (response.isSuccessful) {
                    for (i in response.body()!!) {
                        val food = MencakResponse.FoodResponse(
                            i.id,
                            i.tag,
                            i.hargaTerendah,
                            i.deskripsiMakanan,
                            i.namaMakanan,
                            i.fotoMakanan,
                        )
                        emptyFood.add(food)
                    }
                    _listFood.value = emptyFood
                }
            }

            override fun onFailure(
                call: Call<ArrayList<MencakResponse.FoodResponse>>,
                t: Throwable
            ) {
                Log.d("MainActivity", "onFailure: ${t.message.toString()}")
            }
        })
    }
}