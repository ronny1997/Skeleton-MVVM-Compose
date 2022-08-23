package com.patrisoft.ui.home

import com.patrisoft.core.base.BaseViewModel
import com.patrisoft.core.domain.fold
import com.patrisoft.domain.usecase.GetTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getText: GetTextUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent>(HomeViewState.ShowProgress(true)) {
    init {
        getData(false)
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.RefreshData -> getData(event.error)
        }
    }

    private fun getData(error: Boolean) {
        launch {
            updateViewState(HomeViewState.ShowProgress(true))
            getText(error).fold(
                onSuccess = {
                    updateViewState(HomeViewState.ShowData(it))
                },
                onFailure = {
                    //updateViewState(HomeViewState(false, "", it.message ?: ""))
                }
            )
        }

    }
}