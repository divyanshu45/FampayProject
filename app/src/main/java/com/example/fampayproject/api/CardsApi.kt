package com.example.fampayproject.api

import com.example.fampayproject.model.CardGroupList
import retrofit2.http.GET

interface CardsApi {

    @GET("v3/04a04703-5557-4c84-a127-8c55335bb3b4")
    suspend fun getCardsList() : CardGroupList
}