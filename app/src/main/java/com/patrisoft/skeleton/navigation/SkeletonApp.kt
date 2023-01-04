package com.patrisoft.skeleton.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.patrisoft.ui.NavigationHost
import com.patrisoft.ui.theme.SkeletonMVVMTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SkeletonApp() {
    SkeletonMVVMTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = backstackEntry.value?.destination?.route
        val visible = remember {
            mutableStateOf(true)
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = visible.value,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    BottomNavigationApp(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        ) {
            NavigationHost(navController = navController, visible)
        }
    }
}