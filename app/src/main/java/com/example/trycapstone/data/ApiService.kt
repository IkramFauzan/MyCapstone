package com.example.trycapstone.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("harga")
    fun getResponse(
        @Query("provinsi") provinsi: String
    ): Call<CabaiResponse>

    @GET("harga")
    fun getProvince(
        @Query("provinsi") provinsi: String
    ): Call<ResponseNewItem>

    @GET("data")
    fun getGraphic(
        @Query("provinsi") provinsi: String
    ): Call<CabaiResponse>



}