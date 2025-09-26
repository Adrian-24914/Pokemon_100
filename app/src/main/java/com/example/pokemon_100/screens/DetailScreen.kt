package com.example.pokemon_100.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokemon_100.viewmodel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    pokemonId: Int,
    onBackClick: () -> Unit,
    viewModel: PokemonViewModel = viewModel()
) {
    val uiState by viewModel.detailUiState.collectAsState()

    LaunchedEffect(pokemonId) {
        viewModel.loadPokemonDetail(pokemonId)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearPokemonDetail()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DetailFragment",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6A1B9A) // Purple color matching the reference
                )
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF6A1B9A))
                }
            }
            uiState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
            uiState.pokemon != null -> {
                val pokemon = uiState.pokemon!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Front and Back buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Front",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Back",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Pokemon Images - Front and Back (Normal)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Front Normal
                        val frontDefault = pokemon.sprites.frontDefault
                        if (frontDefault != null) {
                            Image(
                                painter = rememberAsyncImagePainter(frontDefault),
                                contentDescription = "${pokemon.name} front",
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            // Placeholder for missing image
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No Image", color = Color.Gray)
                            }
                        }

                        // Back Normal
                        val backDefault = pokemon.sprites.backDefault
                        if (backDefault != null) {
                            Image(
                                painter = rememberAsyncImagePainter(backDefault),
                                contentDescription = "${pokemon.name} back",
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            // Placeholder for missing image
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No Image", color = Color.Gray)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Front Shiny and Back Shiny buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Front Shiny",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Back Shiny",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Pokemon Images - Front and Back (Shiny)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Front Shiny
                        val frontShiny = pokemon.sprites.frontShiny
                        if (frontShiny != null) {
                            Image(
                                painter = rememberAsyncImagePainter(frontShiny),
                                contentDescription = "${pokemon.name} front shiny",
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            // Placeholder for missing image
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No Image", color = Color.Gray)
                            }
                        }

                        // Back Shiny
                        val backShiny = pokemon.sprites.backShiny
                        if (backShiny != null) {
                            Image(
                                painter = rememberAsyncImagePainter(backShiny),
                                contentDescription = "${pokemon.name} back shiny",
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            // Placeholder for missing image
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No Image", color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}