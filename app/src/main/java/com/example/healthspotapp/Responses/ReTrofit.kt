package com.example.healthspotapp.Responses

import retrofit2.converter.gson.GsonConverterFactory

object ReTrofit {
private const val baseurl="https://wizzie.online/HealthSpot/"

val instance :Api by lazy {
    val retrofit=retrofit2.Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    retrofit.create(Api::class.java)
}
}