package com.example.fampayproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fampayproject.repository.CardsRepo

class ViewModelFactory(val app: Application, val cardsRepo: CardsRepo)  : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardsViewModel(app, cardsRepo) as T
    }
}