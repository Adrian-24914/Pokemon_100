package com.example.pokemon_100.data.repository

import com.example.pokemon_100.data.remote.ApiService
import com.example.pokemon_100.models.PokemonDetail
import com.example.pokemon_100.models.PokemonListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val apiService: ApiService) {

    suspend fun getPokemonList(
        limit: Int = 100,
        offset: Int = 0
    ): Result<PokemonListResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemonList(limit, offset)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(
                        Exception("Error ${response.code()}: ${response.message()}")
                    )
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getPokemonDetail(pokemonId: Int): Result<PokemonDetail> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemonDetail(pokemonId)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(
                        Exception("Error ${response.code()}: ${response.message()}")
                    )
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}