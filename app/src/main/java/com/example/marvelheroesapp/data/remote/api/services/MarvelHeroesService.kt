package com.example.marvelheroesapp.data.remote.api.services

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint
import com.example.marvelheroesapp.data.remote.model.general.MarvelHeroesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelHeroesService {

    @GET(ApiEndpoint.CHARACTERS)
    suspend fun getCharacters(@Query("ts") timeStamp: String,
                              @Query("apikey") privateApiKey: String,
                              @Query("hash") hash: String) : MarvelHeroesResponse
}