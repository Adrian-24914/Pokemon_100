package com.example.pokemon_100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_100.models.PokemonBasic
import com.example.pokemon_100.models.PokemonDetail
import com.example.pokemon_100.network.PokemonApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PokemonListUiState(
    val pokemonList: List<PokemonBasic> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class PokemonDetailUiState(
    val pokemon: PokemonDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class PokemonViewModel : ViewModel() {

    private val _listUiState = MutableStateFlow(PokemonListUiState())
    val listUiState: StateFlow<PokemonListUiState> = _listUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow(PokemonDetailUiState())
    val detailUiState: StateFlow<PokemonDetailUiState> = _detailUiState.asStateFlow()

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            _listUiState.value = _listUiState.value.copy(isLoading = true, error = null)

            try {
                val response = PokemonApi.service.getPokemonList(100, 0)
                if (response.isSuccessful) {
                    response.body()?.let { pokemonListResponse ->
                        _listUiState.value = _listUiState.value.copy(
                            pokemonList = pokemonListResponse.results,
                            isLoading = false
                        )
                    }
                } else {
                    _listUiState.value = _listUiState.value.copy(
                        isLoading = false,
                        error = "Error loading Pokemon list: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _listUiState.value = _listUiState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun loadPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            _detailUiState.value = _detailUiState.value.copy(isLoading = true, error = null)

            try {
                val response = PokemonApi.service.getPokemonDetail(pokemonId)
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        _detailUiState.value = _detailUiState.value.copy(
                            pokemon = pokemon,
                            isLoading = false
                        )
                    }
                } else {
                    _detailUiState.value = _detailUiState.value.copy(
                        isLoading = false,
                        error = "Error loading Pokemon detail: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _detailUiState.value = _detailUiState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun clearPokemonDetail() {
        _detailUiState.value = PokemonDetailUiState()
    }
}
