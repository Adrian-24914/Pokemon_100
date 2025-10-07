package com.example.pokemon_100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_100.data.remote.RetrofitClient
import com.example.pokemon_100.data.repository.MainRepository
import com.example.pokemon_100.models.PokemonBasic
import com.example.pokemon_100.models.PokemonDetail
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

    private val repository = MainRepository(RetrofitClient.apiService)

    private val _listUiState = MutableStateFlow(PokemonListUiState())
    val listUiState: StateFlow<PokemonListUiState> = _listUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow(PokemonDetailUiState())
    val detailUiState: StateFlow<PokemonDetailUiState> = _detailUiState.asStateFlow()

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            _listUiState.value = _listUiState.value.copy(
                isLoading = true,
                error = null
            )

            repository.getPokemonList(100, 0)
                .onSuccess { response ->
                    _listUiState.value = _listUiState.value.copy(
                        pokemonList = response.results,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _listUiState.value = _listUiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Error desconocido al cargar la lista"
                    )
                }
        }
    }

    fun loadPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            _detailUiState.value = _detailUiState.value.copy(
                isLoading = true,
                error = null
            )

            repository.getPokemonDetail(pokemonId)
                .onSuccess { pokemon ->
                    _detailUiState.value = _detailUiState.value.copy(
                        pokemon = pokemon,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _detailUiState.value = _detailUiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Error desconocido al cargar el detalle"
                    )
                }
        }
    }

    fun clearPokemonDetail() {
        _detailUiState.value = PokemonDetailUiState()
    }
}
