//package com.example.mencak.model.repository
//
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.liveData
//import com.dicoding.picodiploma.loginwithanimation.model.UserPreference
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class UserRepository(private val apiService: ApiService, mContext: Context) {
//
//    private var sharedPreferences: UserPreference = UserPreference(mContext)
//
//    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
//        emit(Result.Loading)
//        wrapEspressoIdlingResource {
//            try {
//                val response = apiService.showLogin(email, password)
//                val loginResult = response.loginResult
//
//                val userId = loginResult.userId
//                val name = loginResult.name
//                val token = loginResult.token
//
//                sharedPreferences.saveAuthToken(UserPreference.USER_ID, userId)
//                sharedPreferences.saveAuthToken(UserPreference.NAME, name)
//                sharedPreferences.saveAuthToken(UserPreference.TOKEN, token)
//                emit(Result.Success(response))
//            } catch (e: Exception) {
//                emit(Result.Error(e.message.toString()))
//            }
//        }
//    }
//
//    fun saveUser(
//        name: String, email: String, password: String
//    ): LiveData<Result<RegisterResponse>> = liveData {
//        emit(Result.Loading)
//        wrapEspressoIdlingResource {
//            try {
//                val response = apiService.createRegister(name, email, password)
//                emit(Result.Success(response))
//            } catch (e: Exception) {
//                emit(Result.Error(e.message.toString()))
//            }
//        }
//    }
//
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
//}
//
//
