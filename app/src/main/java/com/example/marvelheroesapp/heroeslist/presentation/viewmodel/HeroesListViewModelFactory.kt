package com.example.marvelheroesapp.heroeslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelheroesapp.data.remote.api.providers.MarvelHeroesProvider
import com.example.marvelheroesapp.data.repository.MarvelHeroesListRepository
import java.lang.IllegalArgumentException

class HeroesListViewModelFactory(private val marvelHeroesProvider: MarvelHeroesProvider) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroesListViewModel::class.java)) {
            return HeroesListViewModel(MarvelHeroesListRepository(marvelHeroesProvider)) as T
        }
        throw IllegalArgumentException(UNKNOWN_CLASS_NAME_ERROR)
    }

    companion object {
        const val UNKNOWN_CLASS_NAME_ERROR = "Unknown Class Name"
    }
}