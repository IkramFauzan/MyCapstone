package com.example.trycapstone.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trycapstone.data.ApiService
import com.example.trycapstone.data.Price
import com.example.trycapstone.data.RetrofitClient
import kotlinx.coroutines.launch

class CabaiViewModel : ViewModel() {
//    private val apiService: ApiService = RetrofitClient.apiService
//
//    private val _cabaiPriceList = MutableLiveData<List<Price>>()
//    val cabaiPriceList: LiveData<List<Price>> get() = _cabaiPriceList
//
//    fun fetchCabaiPrices(province: String) {
//        Log.e("model", "berhasil bang")
//        viewModelScope.launch {
//
//            val priceDataList = mutableListOf<Price>()
//
//
//            try {
//                val cabaiPrice = apiService.getCabaiPriceByProvince(province)
//                val priceList = mutableListOf<Price>()
//
//                // Menambahkan objek Price ke priceList
//                priceList.add(cabaiPrice)
//                _cabaiPriceList.value = priceDataList
//            } catch (e: Exception) {
//                Log.e("model", "Gagal bang")
//                // Handle error jika terjadi kesalahan saat mengambil data
//            }
//
//        }
//    }
}