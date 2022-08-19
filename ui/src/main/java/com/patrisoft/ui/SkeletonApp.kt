package com.patrisoft.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.patrisoft.skeleton.ui.theme.SkeletonMVVMTheme

@Composable
fun SkeletonApp() {
    SkeletonMVVMTheme {
        Text(text = "Hello word")
    }
}