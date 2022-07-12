package com.example.fampayproject.repository

import com.example.fampayproject.api.ApiHelper

class CardsRepo(private val apiHelper: ApiHelper) {
    suspend fun getCards() = apiHelper.getCards()
}