package com.example.mencak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModel(
    var name: String,
    var profil: String,
    var title: String,
    var image: String,
    var comment: Int,
    var tag: String
) : Parcelable