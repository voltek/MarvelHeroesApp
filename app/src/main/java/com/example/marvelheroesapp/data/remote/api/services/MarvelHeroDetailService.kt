package com.example.marvelheroesapp.data.remote.api.services

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint
import com.example.marvelheroesapp.data.remote.model.general.MarvelHeroesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelHeroDetailService {

    @GET(ApiEndpoint.CHARACTERS_DETAIL + "{character_id}")
    suspend fun getCharacterDetail(@Path("character_id") characterId: String,
                                   @Query("ts") timeStamp: String,
                                   @Query("apikey") privateApiKey: String,
                                   @Query("hash") hash: String) : MarvelHeroesResponse
}