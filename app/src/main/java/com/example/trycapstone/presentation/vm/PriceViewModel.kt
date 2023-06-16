package com.example.trycapstone.presentation.vm

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trycapstone.data.*
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.firestore.Query
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceViewModel: ViewModel() {

    private val _listPrice = MutableLiveData<List<Cabai>>()
    val listPrice: LiveData<List<Cabai>> = _listPrice

    private val _dataRespond = MutableLiveData<List<Search>>()
    val dataRespond: LiveData<List<Search>> = _dataRespond

    private val _dataCabai = MutableLiveData<List<Price>>()
    val dataCabai: LiveData<List<Price>> = _dataCabai

    private val _cabaiList = MutableLiveData<CabaiResponse?>()
    val cabaiList: LiveData<CabaiResponse?> = _cabaiList

    private val _priceList = MutableLiveData<List<DataItem>?>()
    val priceList: LiveData<List<DataItem>?> = _priceList

    private val _listData = MutableLiveData<List<Price>>()
    val listData: LiveData<List<Price>> = _listData

    private val _lineChartData = MutableLiveData<LineData>()
    val lineChartData: LiveData<LineData> = _lineChartData

//    init {
//        pencarianUser("Tes")
//    }
//
//    fun pencarian(kataKunci: String){
//        pencarianUser(kataKunci)
//    }

    fun getPrice(provinsi: String) {
        Log.e("PriceViewModel", "Success")
        val client = RetrofitClient.getApiService().getProv(provinsi)
        client.enqueue(object : Callback<PriceData> {
            override fun onResponse(call: Call<PriceData>, response: Response<PriceData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataCabai.value = response.body()?.Price
                    }
                } else {
                    Log.e("PriceViewModel", "Failed")
                }
            }

            override fun onFailure(call: Call<PriceData>, t: Throwable) {
                Log.e("PriceViewModel", "Error: ${t.message}")
            }
        })
    }

    fun pencarianUser(query: String) {
        val client = RetrofitClient.getApiService().getProvince(query)
        client.enqueue(object : Callback<PriceData> {
            override fun onResponse(
                call: Call<PriceData>,
                response: Response<PriceData>
            ) {
                val responseBody = response.body()
                Log.e("Terserah", responseBody.toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataCabai.value = response.body()?.Price
                    }
                } else {
                    Log.e("Terserah", "Muncul error baris 69")
                }
            }
            override fun onFailure(call: Call<PriceData>, t: Throwable) {
                print("Muncul Eror: " + t.message)
            }
        })
    }

}