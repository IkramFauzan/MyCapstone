package com.example.trycapstone.data

import com.google.gson.annotations.SerializedName


data class CabaiResponse(

	@field:SerializedName("data")
	val data: List<Cabai>,

	@field:SerializedName("No.")
	val no: String,

	@field:SerializedName("Provinsi")
	val provinsi: String
)

data class Cabai(
	@field:SerializedName("01/2020")
	val monthYear: String,
	val price: String
)

data class Price(
	val date: String,
	val price: Float
)

data class DataResponse(
	val dataPoints: List<Price>
)

