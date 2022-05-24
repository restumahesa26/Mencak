package com.example.mencak.model.response

import com.google.gson.annotations.SerializedName

class MencakResponse {
    data class RegisterResponse(
        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String
    )


    data class LoginResponse(
        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String,

        @field:SerializedName("loginResult")
        val loginResult: LoginResultResponse
    )

    data class LoginResultResponse(
        @field:SerializedName("userId")
        val userId: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("token")
        val token: String
    )

//    data class NewStoryResponse(
//        @field:SerializedName("error")
//        val error: Boolean,
//
//        @field:SerializedName("message")
//        val message: String
//    )
//
//    data class AllStoryResponse(
//        @field:SerializedName("error")
//        val error: Boolean,
//
//        @field:SerializedName("message")
//        val message: String,
//
//        @field:SerializedName("listStory")
//        val loginStory: List<ListStoryResponse>
//    )
//
//    data class ListStoryResponse(
//        @field:SerializedName("name")
//        val name: String,
//
//        @field:SerializedName("description")
//        val description: String,
//
//        @field:SerializedName("photoUrl")
//        val photoUrl: String,
//
//        @field:SerializedName("lat")
//        val lat: Float,
//
//        @field:SerializedName("lon")
//        val lon: Float
//    )
}