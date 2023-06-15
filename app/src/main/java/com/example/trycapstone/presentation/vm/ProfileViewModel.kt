package com.example.trycapstone.presentation.vm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trycapstone.Repo
import com.example.trycapstone.User
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class ProfileViewModel: ViewModel() {

    private val _imageBitmap = MutableLiveData<Bitmap?>()
    val imageBitmap: LiveData<Bitmap?> = _imageBitmap

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> = _error

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

}