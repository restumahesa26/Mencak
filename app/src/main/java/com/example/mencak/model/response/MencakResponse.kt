package com.example.mencak.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

    @Parcelize
    data class FoodResponse(
        @field:SerializedName("id")
        var id: String,

        @field:SerializedName("tag")
        var tag: String,

        @field:SerializedName("hargaTerendah")
        var hargaTerendah: String,

        @field:SerializedName("deskripsiMakanan")
        var deskripsiMakanan: String,

        @field:SerializedName("namaMakanan")
        var namaMakanan: String,

        @field:SerializedName("fotoMakanan")
        var fotoMakanan: String
    ) : Parcelable

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