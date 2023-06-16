package com.example.trycapstone.presentation.vm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trycapstone.Repo
import com.example.trycapstone.User
import com.example.trycapstone.data.Price
import com.example.trycapstone.data.PriceData
import com.example.trycapstone.data.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class ProfileViewModel: ViewModel() {

    private val _imageBitmap = MutableLiveData<Bitmap?>()
    val imageBitmap: LiveData<Bitmap?> = _imageBitmap

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> = _error

    private val _dataCabai = MutableLiveData<List<Price>>()
    val dataCabai: LiveData<List<Price>> = _dataCabai

    init{
        getProfile()
//        getPatients()
    }

    fun storeImage(bitmap: Bitmap, quality: Int) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val compressedBitmap =
            BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size())
        _imageBitmap.value = compressedBitmap
    }

    private fun getProfile(){
        Repo.getUserData(
            onSuccess = {
                _userData.value = it

            },
            onFailure = {
                _error.value = it
            }
        )
    }

    fun updateProfile(user: User){
        viewModelScope.launch {
            try{
                Repo.createOrUpdateUserData(user)
            }catch (e:Exception){
                _error.value = e
            }
        }

    }

    fun updateProfile(user: User, file: Bitmap){
        viewModelScope.launch {
            try{
                Repo.createOrUpdateUserData(user,file)
            }catch (e:Exception){
                _error.value = e
            }
        }

    }

    fun getPrice(provinsi: String) {
        Log.e("PriceViewModel", "Success")
        val client = RetrofitClient.getApiService().getProv(provinsi)
        client.enqueue(object : Callback<PriceData> {
            override fun onResponse(call: Call<PriceData>, response: Response<PriceData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataCabai.postValue(response.body()?.prices)
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
                        _dataCabai.postValue(response.body()?.prices)
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