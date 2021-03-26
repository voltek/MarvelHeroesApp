package com.example.marvelheroesapp.heroeslist.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelheroesapp.R
import com.example.marvelheroesapp.data.remote.api.providers.MarvelHeroesProvider
import com.example.marvelheroesapp.data.remote.model.general.Result
import com.example.marvelheroesapp.data.utils.Status
import com.example.marvelheroesapp.heroeslist.presentation.viewmodel.HeroesListViewModel
import com.example.marvelheroesapp.heroeslist.presentation.viewmodel.HeroesListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class HeroesListActivity : AppCompatActivity() {

    private lateinit var viewModel: HeroesListViewModel
    private lateinit var heroesAdapter: HeroesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            HeroesListViewModelFactory(MarvelHeroesProvider())
        ).get(HeroesListViewModel::class.java)
    }

    private fun setupRecyclerView() {
        rvHeroesList.layoutManager = LinearLayoutManager(this)
        heroesAdapter = HeroesListAdapter(arrayListOf())
        rvHeroesList.adapter = heroesAdapter
    }

    private fun setupObservers() {
        viewModel.getMarvelHeroes().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rvHeroesList.visibility = View.VISIBLE
                        pbLoader.visibility = View.GONE
                        resource.data?.let { heroesResponse ->
                                retrieveLisToAdapter(heroesResponse.data.results)
                        }
                    }
                    Status.ERROR -> {
                        rvHeroesList.visibility = View.VISIBLE
                        pbLoader.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        pbLoader.visibility = View.VISIBLE
                        rvHeroesList.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveLisToAdapter(heroes: List<Result>) {
        heroesAdapter.apply {
            addHeroes(heroes)
            notifyDataSetChanged()
        }
    }
}