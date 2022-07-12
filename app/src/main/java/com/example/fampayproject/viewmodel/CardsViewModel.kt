package com.example.fampayproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fampayproject.repository.CardsRepo
import com.example.fampayproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class CardsViewModel(application: Application, private val cardsRepo: CardsRepo) : ViewModel(){

    fun getCardsList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = cardsRepo.getCards()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}