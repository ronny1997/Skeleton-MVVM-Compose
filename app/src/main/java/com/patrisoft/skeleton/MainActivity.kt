package com.patrisoft.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.patrisoft.skeleton.navigation.SkeletonApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkeletonApp()
        }
    }
}