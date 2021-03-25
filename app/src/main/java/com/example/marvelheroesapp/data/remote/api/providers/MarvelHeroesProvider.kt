package com.example.marvelheroesapp.data.remote.api.providers

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint.PRIVATE_API_KEY
import com.example.marvelheroesapp.data.remote.api.ApiHelper
import com.example.marvelheroesapp.data.remote.api.services.MarvelHeroesService
import com.example.marvelheroesapp.data.utils.ApiUtils

class MarvelHeroesProvider {

    private val marvelHeroesListService: MarvelHeroesService by lazy {
        ApiHelper.createRetrofitConnection().create(MarvelHeroesService::class.java)
    }

    suspend fun getMarvelHeroes() {
        val timeStamp = ApiUtils().getCurrentTimeStampInSeconds()
        val hash = ApiUtils().getMD5Hash(timeStamp)

        marvelHeroesListService.getCharacters(timeStamp, PRIVATE_API_KEY, hash)
    }

}