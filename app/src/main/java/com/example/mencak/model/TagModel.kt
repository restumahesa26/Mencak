package com.example.mencak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TagModel(
    var name: String,
) : Parcelable