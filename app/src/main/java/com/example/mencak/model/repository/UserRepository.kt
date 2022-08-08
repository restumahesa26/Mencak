package com.example.mencak.model.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mencak.model.Result
import com.example.mencak.model.UserPreference
import com.example.mencak.model.api.ApiService
import com.example.mencak.model.response.MencakResponse.RegisterResponse
import com.example.mencak.model.response.MencakResponse.LoginResponse
import com.example.mencak.model.wrapEspressoIdlingResource
import com.google.firebase.auth.FirebaseAuth

class UserRepository(private val apiService: ApiService, mContext: Context) {

    private var sharedPreferences: UserPreference = UserPreference(mContext)
    private lateinit var auth: FirebaseAuth

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                }else {

                }
            }
    }

    fun saveUser(
        name: String, email: String, password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        wrapEspressoIdlingResource {
            try {
                val response = apiService.createRegister(name, email, password)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

//    fun getMapsStory(): LiveData<Result<List<ListStoryResponse>>> = liveData {
//        emit(Result.Loading)
//        wrapEspressoIdlingResource {
//            try {
//                val response =
//                    apiService.getMapsAllStories(" Bearer ${sharedPreferences.fetchAuthToken()}")
//                val loginStory = response.loginStory
//
//                val listMapStory = loginStory.map { i ->
//                    ListStoryResponse(i.name, i.description, i.photoUrl, i.lat, i.lon)
//                }
//                emit(Result.Success(listMapStory))
//            } catch (e: Exception) {
//                emit(Result.Error(e.message.toString()))
//                Log.d("MainActivity", "onFailure: ${e.message.toString()}")
//            }
//        }
//    }
}


