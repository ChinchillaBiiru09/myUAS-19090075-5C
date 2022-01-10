package com.example.uas19090075_mobileapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //base url => alamat utama dari rest api yang digunakan
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    //agar dapat dipanggil dari mana saja
    val instance: API by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(API::class.java)
    }
}