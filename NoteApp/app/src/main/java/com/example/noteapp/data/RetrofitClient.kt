package com.example.noteapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // lancer Strapi : "npm run develop" dans Windows Powershell et pas cmd//
    // Strapi token : 9515dc1f20126893101d899b5497fbba6be68cc329036767eb1555fe2b55016c0e4d977f155d735551b75a861aca9b4c58e30d2bf75e25a312f7325a96295d9c099df8f0f6276dacbd74e7837e814d510c32a1f5f54d87925632d0b91a205d3df2bb45e0fd0defaa683b593ef5485a89b3db0da327d88bf8c92400c8c43c7137

    private const val BASE_URL = "http://10.0.2.2:1337/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myApiService: MyApiService = retrofit.create(MyApiService::class.java)
}