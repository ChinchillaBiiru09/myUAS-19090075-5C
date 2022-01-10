package com.example.uas19090075_mobileapp.Model

import com.google.gson.annotations.SerializedName

data class CreatePostResponse(
    val userId: String?,
    val id: Int,
    val title: String?,
    @SerializedName("body")
    val text: String?
)
