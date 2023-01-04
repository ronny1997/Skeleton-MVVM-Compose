package com.patrisoft.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(var route: String, var icon: ImageVector, var title: String){
    object Home : Screen("HomeScreen", Icons.Filled.Home, "Home")
    object Planets : Screen("Planets", Icons.Filled.Place, "Place")
}

sealed class Tabs(var name: String){
    object Hoy : Tabs("Hoy")
    object Mañana : Tabs("<Mañana")
    object Historial : Tabs("Historial")
}