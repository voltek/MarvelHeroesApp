package com.example.marvelheroesapp.data.repository

import com.example.marvelheroesapp.data.remote.api.providers.MarvelHeroesProvider

class MarvelHeroesListRepository(private val marvelHeroesProvider: MarvelHeroesProvider) {

    suspend fun getMarvelHeroes() = marvelHeroesProvider.getMarvelHeroes()
}