package com.patrisoft.ui.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@Composable
fun HomeView(visible: MutableState<Boolean>) {
    Text(text = "Hola")
}