package com.example.marvelheroesapp.data.repository

import com.example.marvelheroesapp.data.remote.api.providers.MarvelHeroDetailProvider
import com.example.marvelheroesapp.data.remote.api.providers.MarvelHeroesProvider

class MarvelHeroDetailRepository(
    private val marvelHeroDetailProvider: MarvelHeroDetailProvider,
    private val marvelHeroId: String
) {

    suspend fun getMarvelHeroDetail() = marvelHeroDetailProvider.getMarvelHeroDetail(marvelHeroId)
}