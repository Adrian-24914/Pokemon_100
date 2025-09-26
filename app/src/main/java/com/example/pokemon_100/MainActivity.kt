package com.example.pokemon_100

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemon_100.screens.DetailScreen
import com.example.pokemon_100.screens.MainScreen
import com.example.pokemon_100.ui.theme.Pokemon_100Theme
import com.example.pokemon_100.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pokemon_100Theme {
                PokemonApp()
            }
        }
    }
}

@Composable
fun PokemonApp() {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel()

    PokemonNavigation(
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun PokemonNavigation(
    navController: NavHostController,
    viewModel: PokemonViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate("detail/$pokemonId")
                },
                viewModel = viewModel
            )
        }

        composable("detail/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull() ?: 1
            DetailScreen(
                pokemonId = pokemonId,
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }
}