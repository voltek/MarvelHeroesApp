package com.example.marvelheroesapp.herodetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelheroesapp.data.remote.api.providers.MarvelHeroDetailProvider
import com.example.marvelheroesapp.data.repository.MarvelHeroDetailRepository

class HeroDetailViewModelFactory(
    private val marvelHeroDetailProvider: MarvelHeroDetailProvider,
    private val marvelHeroId: String
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroDetailViewModel::class.java)) {
            return HeroDetailViewModel(
                MarvelHeroDetailRepository(marvelHeroDetailProvider, marvelHeroId)
            ) as T
        }
        throw IllegalArgumentException(UNKNOWN_CLASS_NAME_ERROR)
    }

    companion object {
        const val UNKNOWN_CLASS_NAME_ERROR = "Unknown Class Name"
    }
}