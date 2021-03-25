package com.example.marvelheroesapp.data.remote.model.general

data class Result(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val series: Comics,
    val stories: Comics,
)
