package com.example.marvelheroesapp.data.remote.api.providers

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint.PUBLIC_API_KEY
import com.example.marvelheroesapp.data.remote.api.ApiHelper
import com.example.marvelheroesapp.data.remote.api.services.MarvelHeroesService
import com.example.marvelheroesapp.data.remote.model.general.MarvelHeroesResponse
import com.example.marvelheroesapp.utils.GeneralUtils

class MarvelHeroesProvider {

    private val marvelHeroesListService: MarvelHeroesService by lazy {
        ApiHelper.createRetrofitConnection().create(MarvelHeroesService::class.java)
    }

    suspend fun getMarvelHeroes() : MarvelHeroesResponse {
        val timeStamp = GeneralUtils.getCurrentTimeStampInSeconds()
        val hash = GeneralUtils.getMD5Hash(timeStamp)

        return marvelHeroesListService.getCharacters(timeStamp, PUBLIC_API_KEY, hash)
    }

}