package com.example.fampayproject.api

import android.content.Context
import com.example.fampayproject.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    fun getInstance() : CardsApi{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardsApi::class.java)
    }
}