package com.example.trycapstone.data

import com.example.trycapstone.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class RetrofitClient {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                if(BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                } else {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://chilly-prediction.et.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }

//        private fun createOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//            val trustManagerFactory =
//                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).also {
//                    it.init(null)
//                }
//            val trustManagers = trustManagerFactory.trustManagers
//
//            val sslContext = SSLContext.getInstance("TLS")
//            sslContext.init(null, trustManagers, null)
//
//            val sslSocketFactory = sslContext.socketFactory
//
//            return OkHttpClient.Builder()
//                .sslSocketFactory(sslSocketFactory, trustManagers[0] as X509TrustManager)
//                .addInterceptor(loggingInterceptor)
//                .build()
    }
}