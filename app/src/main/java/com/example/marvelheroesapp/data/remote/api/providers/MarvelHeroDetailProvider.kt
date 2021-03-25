package com.example.marvelheroesapp.data.remote.api.providers

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint
import com.example.marvelheroesapp.data.remote.api.ApiHelper
import com.example.marvelheroesapp.data.remote.api.services.MarvelHeroDetailService
import com.example.marvelheroesapp.data.utils.ApiUtils

class MarvelHeroDetailProvider {

    private val marvelHeroDetailService: MarvelHeroDetailService by lazy {
        ApiHelper.createRetrofitConnection().create(MarvelHeroDetailService::class.java)
    }

    suspend fun getMarvelHeroDetail(marvelHeroId: String) {
        val timeStamp = ApiUtils().getCurrentTimeStampInSeconds()
        val hash = ApiUtils().getMD5Hash(timeStamp)

        marvelHeroDetailService.getCharacterDetail(
            marvelHeroId, timeStamp,
            ApiEndpoint.PRIVATE_API_KEY, hash
        )
    }
}