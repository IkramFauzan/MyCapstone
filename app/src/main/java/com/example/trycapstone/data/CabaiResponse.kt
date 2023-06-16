package com.example.trycapstone.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CabaiResponse(

	@field:SerializedName("data")
	val data: List<Search>,

	@field:SerializedName("No.")
	val no: String,

	@field:SerializedName("Provinsi")
	val provinsi: String
): Parcelable

@Parcelize
data class Cabai(
	val monthYear: String,
	val price: String
): Parcelable

@Parcelize
data class PriceData(
	@field:SerializedName("No.")
	val No: Int,
	@field:SerializedName("Provinsi")
	val Provinsi: String,
	@field:SerializedName("Start_date")
	val Start_date: String,
	@field:SerializedName("End_date")
	val End_date: String,
	@field:SerializedName("Price_date")
	val Predict_date: String,
	@field:SerializedName("Price")
	val Price: List<Price>
): Parcelable

@Parcelize
data class Price(
	@field:SerializedName("Date")
	val Date: String,
	@field:SerializedName("Value")
	val Value: String
): Parcelable

@Parcelize
data class Search(
	@SerializedName("No.")
	val number: String,
	@SerializedName("Provinsi")
	val province: String,
	@SerializedName("delapanDuaPuluh")
	val price1: String,
	@SerializedName("delapanDuaPuluhDua")
	val price2: String,
	@SerializedName("delapanDuaPuluhSatu")
	val price3: String,
	@SerializedName("duaDuaPuluh")
	val price4: String,
	@SerializedName("duaDuaPuluhDua")
	val price5: String,
	@SerializedName("duaDuaPuluhSatu")
	val price6: String,
	@SerializedName("duaDuaPuluhTiga")
	val price7: String,
	@SerializedName("duabelasDuaPuluh")
	val price8: String,
	@SerializedName("duabelasDuaPuluhDua")
	val price9: String,
	@SerializedName("duabelasDuaPuluhSatu")
	val price10: String,
	@SerializedName("empatDuaPuluh")
	val price11: String,
	@SerializedName("empatDuaPuluhDua")
	val price12: String,
	@SerializedName("empatDuaPuluhSatu")
	val price13: String,
	@SerializedName("empatDuaPuluhTiga")
	val price14: String,
	@SerializedName("enamDuaPuluh")
	val price15: String,
	@SerializedName("enamDuaPuluhDua")
	val price16: String,
	@SerializedName("enamDuaPuluhSatu")
	val price17: String,
	@SerializedName("limaDuaPuluh")
	val price18: String,
	@SerializedName("limaDuaPuluhDua")
	val price19: String,
	@SerializedName("limaDuaPuluhSatu")
	val price20: String,
	@SerializedName("limaDuaPuluhTiga")
	val price21: String,
	@SerializedName("satuDuaPuluh")
	val price22: String,
	@SerializedName("satuDuaPuluhDua")
	val price23: String,
	@SerializedName("satuDuaPuluhSatu")
	val price24: String,
	@SerializedName("satuDuaPuluhTiga")
	val price25: String,
	@SerializedName("sebelasDuaPuluh")
	val price26: String,
	@SerializedName("sebelasDuaPuluhDua")
	val price27: String,
	@SerializedName("sebelasDuaPuluhSatu")
	val price28: String,
	@SerializedName("sembilanDuaPuluh")
	val price29: String,
	@SerializedName("sembilanDuaPuluhDua")
	val price30: String,
	@SerializedName("sembilanDuaPuluhSatu")
	val price31: String,
	@SerializedName("sepuluhDuaPuluh")
	val price32: String,
	@SerializedName("sepuluhDuaPuluhDua")
	val price33: String,
	@SerializedName("sepuluhDuaPuluhSatu")
	val price34: String,
	@SerializedName("tigaDuaPuluh")
	val price35: String,
	@SerializedName("tigaDuaPuluhDua")
	val price36: String,
	@SerializedName("tigaDuaPuluhSatu")
	val price37: String,
	@SerializedName("tigaDuaPuluhTiga")
	val price38: String,
	@SerializedName("tujuhDuaPuluh")
	val price39: String,
	@SerializedName("tujuhDuaPuluhDua")
	val price40: String,
	@SerializedName("tujuhDuaPuluhSatu")
	val price41: String
): Parcelable

