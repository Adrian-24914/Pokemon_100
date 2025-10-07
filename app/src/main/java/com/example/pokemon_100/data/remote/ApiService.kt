package com.example.pokemon_100.data.remote

import com.example.pokemon_100.models.PokemonDetail
import com.example.pokemon_100.models.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): Response<PokemonDetail>
}