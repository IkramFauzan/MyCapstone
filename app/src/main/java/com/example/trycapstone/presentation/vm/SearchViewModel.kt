package com.example.trycapstone.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trycapstone.data.CabaiResponse
import com.example.trycapstone.data.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

//    private val _searchProv = MutableLiveData<CabaiResponse>()
//    val searchProv: LiveData<CabaiResponse> = _searchProv
//
//    init {
//        searchProvince("Tes")
//    }
//
//    fun pencarian(kataKunci: String){
//        searchProvince(kataKunci)
//    }
//
//    private fun searchProvince(kataKunci: String) {
//        val client = RetrofitClient.getApiService().searchUsers(kataKunci)
//        client.enqueue(object : Callback<GithubResponse> {
//            override fun onResponse(
//                call: Call<GithubResponse>,
//                response: Response<GithubResponse>
//            ) {
//                _isLoading.value = false
//                val responseBody = response.body()
//                Log.e("Terserah", responseBody.toString())
//                if (response.isSuccessful && responseBody != null) {
//                    _dataRespond.value = responseBody
//                } else {
//                    Log.e("Terserah", "Muncul error baris 69")
//                }
//            }
//            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
//                _isLoading.value = false
//                print("Muncul Eror: " + t.message)
//            }
//        })
//    }
}