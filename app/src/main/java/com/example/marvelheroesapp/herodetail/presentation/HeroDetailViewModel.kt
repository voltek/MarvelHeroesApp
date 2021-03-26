package com.example.marvelheroesapp.herodetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.marvelheroesapp.data.repository.MarvelHeroDetailRepository
import com.example.marvelheroesapp.utils.GeneralUtils
import com.example.marvelheroesapp.data.utils.Resource
import kotlinx.coroutines.Dispatchers

class HeroDetailViewModel(
    private val marvelHeroDetailRepository: MarvelHeroDetailRepository
) :
    ViewModel() {

    fun getMarvelHeroDetail() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = marvelHeroDetailRepository.getMarvelHeroDetail()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: GeneralUtils.UNKNOWN_ERROR))
        }
    }
}