package com.example.mencak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentModel(
    var name: String,
    var comment: String,
    var image: String
) : Parcelable