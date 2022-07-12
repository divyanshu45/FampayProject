package com.example.fampayproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fampayproject.adapters.CardsAdapter
import com.example.fampayproject.api.ApiHelper
import com.example.fampayproject.api.RetrofitInstance
import com.example.fampayproject.databinding.ActivityMainBinding
import com.example.fampayproject.model.CardGroup
import com.example.fampayproject.repository.CardsRepo
import com.example.fampayproject.utils.AppPreferences
import com.example.fampayproject.utils.Status
import com.example.fampayproject.viewmodel.CardsViewModel
import com.example.fampayproject.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CardsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var cardsAdapter: CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding.root

        AppPreferences.init(application)

        setContentView(view)
        setupViewModel()
        setupUI()
        getCardsList()
        setupRefreshLayout()
    }

    private fun setupUI(){
        binding.rvCards.layoutManager = LinearLayoutManager(this)
        cardsAdapter = CardsAdapter(arrayListOf(), this)
        binding.rvCards.adapter = cardsAdapter
        binding.btnRetry.setOnClickListener {
            getCardsList()
        }
    }

    private fun setupRefreshLayout(){
        binding.swipeRefresh.setOnRefreshListener {
            setupUI()
            getCardsList()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupViewModel() {
        val countryRepository = CardsRepo(ApiHelper(RetrofitInstance.getInstance()))
        val viewModelProviderFactory = ViewModelFactory(application, countryRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory)[CardsViewModel::class.java]
    }

    private fun getCardsList(){
        viewModel.getCardsList().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { it ->
                            binding.rvCards.visibility = View.VISIBLE
                            binding.btnRetry.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                            retrieveList(it.card_groups as ArrayList<CardGroup>)
                            println(it.toString())
                        }
                    }
                    Status.ERROR -> {
                        binding.rvCards.visibility = View.VISIBLE
                        binding.btnRetry.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Check Your Connection", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.rvCards.visibility = View.GONE
                        binding.btnRetry.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(cards: ArrayList<CardGroup>){
        cardsAdapter.apply {
            addCards(cards)
            notifyDataSetChanged()
        }
    }
}