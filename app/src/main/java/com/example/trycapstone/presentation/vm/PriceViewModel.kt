package com.example.trycapstone.presentation.vm

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trycapstone.data.*
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceViewModel: ViewModel() {

    private val _listPrice = MutableLiveData<List<Cabai>>()
    val listPrice: LiveData<List<Cabai>> = _listPrice

    private val _priceList = MutableLiveData<List<DataItem>?>()
    val priceList: LiveData<List<DataItem>?> = _priceList

    private val _listData = MutableLiveData<List<Price>>()
    val listData: LiveData<List<Price>> = _listData

    private val _lineChartData = MutableLiveData<LineData>()
    val lineChartData: LiveData<LineData> = _lineChartData

    fun getPrice(provinsi: String) {
        Log.e("PriceViewModel", "Success")
        val client = RetrofitClient.getApiService().getProvince(provinsi)
        client.enqueue(object : Callback<ResponseNewItem> {
            override fun onResponse(call: Call<ResponseNewItem>, response: Response<ResponseNewItem>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _priceList.value = responseBody?.data as List<DataItem>?
                } else {
                    Log.e("PriceViewModel", "Failed")
                }
            }

            override fun onFailure(call: Call<ResponseNewItem>, t: Throwable) {
                Log.e("PriceViewModel", "Error: ${t.message}")
            }
        })
    }

//    fun getGraphic(provinsi: String) {
//        Log.e("PriceViewModel", "Success")
//        val client = RetrofitClient.getApiService().getGraphic(provinsi)
//        client.enqueue(object : Callback<DataResponse> {
//            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
//                if (response.isSuccessful) {
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        if (responseBody != null) {
//                            val data = responseBody.getDataForLineChart()
//                            _listData.value = data
//                        }
//                    }
//                } else {
//                    Log.e("PriceViewModel", "Failed")
//                }
//            }
//
//            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
//                Log.e("PriceViewModel", "Error: ${t.message}")
//            }
//        })
//    }


}