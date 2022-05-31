package com.example.mencak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodModel(
    var name: String,
    var image: String,
) : Parcelable