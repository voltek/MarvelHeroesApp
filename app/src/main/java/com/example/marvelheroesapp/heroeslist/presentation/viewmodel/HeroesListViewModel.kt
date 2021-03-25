package com.example.marvelheroesapp.heroeslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.marvelheroesapp.data.repository.MarvelHeroesListRepository
import com.example.marvelheroesapp.data.utils.ApiUtils.Companion.UNKNOWN_ERROR
import com.example.marvelheroesapp.data.utils.Resource
import kotlinx.coroutines.Dispatchers

class HeroesListViewModel(private val marvelHeroesRepository: MarvelHeroesListRepository) :
    ViewModel() {

    fun getMarvelHeroes() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = marvelHeroesRepository.getMarvelHeroes()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: UNKNOWN_ERROR))
        }
    }
}