package com.example.mencak.model.api

import com.example.mencak.model.response.MencakResponse.LoginResponse
import com.example.mencak.model.response.MencakResponse.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register")
    @FormUrlEncoded
    suspend fun createRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun showLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

//    @Multipart
//    @POST("/v1/stories")
//    fun createNewStory(
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Header("Authorization") authorization: String?
//    ): Call<NewStoryResponse>
//
//    @GET("stories")
//    suspend fun getAllStories(
//        @Header("Authorization") authorization: String?,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): AllStoryResponse
//
//    @GET("stories?location=1")
//    suspend fun getMapsAllStories(
//        @Header("Authorization") authorization: String?
//    ): AllStoryResponse
}