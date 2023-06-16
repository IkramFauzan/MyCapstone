package com.example.trycapstone.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("harga/{provinsi}")
    fun getResponse(
        @Path("provinsi") provinsi: String?
    ): Call<CabaiResponse>

    @GET("search")
    fun getProvince(
        @Query("q") provinsi: String?
    ): Call<PriceData>

    @GET("data")
    fun getGraphic(
        @Query("provinsi") provinsi: String?
    ): Call<CabaiResponse>

    @GET("harga/{provinsi}")
    fun getProv(
        @Path("provinsi") provinsi: String?
    ): Call<PriceData>


}