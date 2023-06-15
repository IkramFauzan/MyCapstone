package com.example.trycapstone.data

import com.google.gson.annotations.SerializedName

data class ResponseNew(

	@field:SerializedName("ResponseNew")
	val responseNew: List<ResponseNewItem?>? = null
)

data class ResponseNewItem(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("Provinsi")
	val provinsi: String? = null,

	@field:SerializedName("No.")
	val no: String? = null
)

data class DataItem(

	@field:SerializedName("05/2023")
	val jsonMember052023: String? = null,

	@field:SerializedName("04/2023")
	val jsonMember042023: String? = null,

	@field:SerializedName("03/2023")
	val jsonMember032023: String? = null,

	@field:SerializedName("02/2023")
	val jsonMember022023: String? = null,

	@field:SerializedName("01/2023")
	val jsonMember012023: String? = null,

	@field:SerializedName("12/2022")
	val jsonMember122022: String? = null,

	@field:SerializedName("11/2022")
	val jsonMember112022: String? = null,

	@field:SerializedName("10/2022")
	val jsonMember102022: String? = null,

	@field:SerializedName("09/2022")
	val jsonMember092022: String? = null,

	@field:SerializedName("08/2022")
	val jsonMember082022: String? = null,

	@field:SerializedName("07/2022")
	val jsonMember072022: String? = null,

	@field:SerializedName("06/2022")
	val jsonMember062022: String? = null,

	@field:SerializedName("05/2022")
	val jsonMember052022: String? = null,

	@field:SerializedName("04/2022")
	val jsonMember042022: String? = null,

	@field:SerializedName("03/2022")
	val jsonMember032022: String? = null,

	@field:SerializedName("02/2022")
	val jsonMember022022: String? = null,

	@field:SerializedName("01/2022")
	val jsonMember012022: String? = null,

	@field:SerializedName("12/2021")
	val jsonMember122021: String? = null,

	@field:SerializedName("11/2021")
	val jsonMember112021: String? = null,

	@field:SerializedName("10/2021")
	val jsonMember102021: String? = null,

	@field:SerializedName("09/2021")
	val jsonMember092021: String? = null,

	@field:SerializedName("08/2021")
	val jsonMember082021: String? = null,

	@field:SerializedName("07/2021")
	val jsonMember072021: String? = null,

	@field:SerializedName("06/2021")
	val jsonMember062021: String? = null,

	@field:SerializedName("05/2021")
	val jsonMember052021: String? = null,

	@field:SerializedName("04/2021")
	val jsonMember042021: String? = null,

	@field:SerializedName("03/2021")
	val jsonMember032021: String? = null,

	@field:SerializedName("02/2021")
	val jsonMember022021: String? = null,

	@field:SerializedName("01/2021")
	val jsonMember012021: String? = null,

	@field:SerializedName("12/2020")
	val jsonMember122020: String? = null,

	@field:SerializedName("11/2020")
	val jsonMember112020: String? = null,

	@field:SerializedName("10/2020")
	val jsonMember102020: String? = null,

	@field:SerializedName("09/2020")
	val jsonMember092020: String? = null,

	@field:SerializedName("08/2020")
	val jsonMember082020: String? = null,

	@field:SerializedName("07/2020")
	val jsonMember072020: String? = null,

	@field:SerializedName("06/2020")
	val jsonMember062020: String? = null,

	@field:SerializedName("05/2020")
	val jsonMember052020: String? = null,

	@field:SerializedName("04/2020")
	val jsonMember042020: String? = null,

	@field:SerializedName("03/2020")
	val jsonMember032020: String? = null,

	@field:SerializedName("02/2020")
	val jsonMember022020: String? = null,

	@field:SerializedName("01/2020")
	val jsonMember012020: String? = null
)
