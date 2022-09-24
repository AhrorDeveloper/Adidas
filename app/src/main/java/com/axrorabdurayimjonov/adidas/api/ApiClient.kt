package com.axrorabdurayimjonov.adidas.api

import android.content.ContentValues
import android.util.Log
import com.axrorabdurayimjonov.adidas.fragment.token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val client = OkHttpClient.Builder().addInterceptor{ chain: Interceptor.Chain->
        val newRequest:Request = chain.request().newBuilder().header("Authorization","Bearer $token")
            .build()
        chain.proceed(newRequest)
    }
        .build()
    private const val BASE_URL = "https://adidas.crud.uz/"
    fun getRetrofit():ApiService{
        Log.e(ContentValues.TAG, "getRetrofit: $token")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}