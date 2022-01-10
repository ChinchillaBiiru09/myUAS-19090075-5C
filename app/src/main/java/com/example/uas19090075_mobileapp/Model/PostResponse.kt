package com.example.uas19090075_mobileapp.Model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    val userId: Int,
    //variabel disini harus sama dengan yg ada di response
    val id: Int,
    val title: String?,
    @SerializedName("body")
    val text: String?
    //anotasi ini digunakan semisal ingin membuat variable dengan nama yg berbeda dari var json
)