package com.patrisoft.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patrisoft.core.Screen
import com.patrisoft.ui.home.HomeView
import com.patrisoft.ui.planets.PlanetsView

@Composable
fun NavigationHost(navController: NavHostController, visible: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
           HomeView(visible = visible)
        }
        composable(Screen.Planets.route) {
            PlanetsView()
        }
    }
}