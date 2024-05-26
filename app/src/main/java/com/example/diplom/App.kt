package com.example.diplom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost


@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        //composable("map") { MapScreen(navController) }
        //composable("item") { ItemScreen(navController) }
        //composable("bag") { BagScreen(navController) }
        //composable("prevOrder") { PrevOrderScreen(navController) }
    }
}