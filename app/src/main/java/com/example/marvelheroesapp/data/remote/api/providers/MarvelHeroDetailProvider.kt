package com.example.marvelheroesapp.data.remote.api.providers

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint
import com.example.marvelheroesapp.data.remote.api.ApiHelper
import com.example.marvelheroesapp.data.remote.api.services.MarvelHeroDetailService
import com.example.marvelheroesapp.data.remote.model.general.MarvelHeroesResponse
import com.example.marvelheroesapp.utils.GeneralUtils

class MarvelHeroDetailProvider {

    private val marvelHeroDetailService: MarvelHeroDetailService by lazy {
        ApiHelper.createRetrofitConnection().create(MarvelHeroDetailService::class.java)
    }

    suspend fun getMarvelHeroDetail(marvelHeroId: String) : MarvelHeroesResponse {
        val timeStamp = GeneralUtils.getCurrentTimeStampInSeconds()
        val hash = GeneralUtils.getMD5Hash(timeStamp)

        return marvelHeroDetailService.getCharacterDetail(
            marvelHeroId, timeStamp,
            ApiEndpoint.PRIVATE_API_KEY, hash
        )
    }
}