package com.patrisoft.ui.home


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.patrisoft.core.base.BaseViewModel
import com.patrisoft.core.base.ViewState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


@Composable
fun HomeView(
    visible: MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var text by remember {
        mutableStateOf("---")
    }
    var showProgressbar by remember {
        mutableStateOf(false)
    }

    val state by viewModel.viewState.collectAsState()
    when (state) {
        is HomeViewState.ShowProgress -> LinearProgressIndicator()
        is HomeViewState.ShowData -> {
            text = (state as HomeViewState.ShowData).text
        }
        is HomeViewState.ShowError -> {
            text = (state as HomeViewState.ShowData).text
        }
    }

    Column() {
        Text(text = text)
        Button(onClick = {
            viewModel.onEvent(HomeViewEvent.RefreshData(false))
        }) {
            Text(text = "Refresh Data")
        }
    }

}
