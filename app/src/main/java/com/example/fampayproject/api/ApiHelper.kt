package com.example.fampayproject.api

class ApiHelper(private val api: CardsApi){
    suspend fun getCards() = api.getCardsList()
}