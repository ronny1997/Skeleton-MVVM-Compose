package com.patrisoft.ui.home

import com.patrisoft.core.base.BaseViewModel
import com.patrisoft.core.domain.UseCase
import com.patrisoft.core.domain.fold
import com.patrisoft.domain.usecase.GetDataReeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getData: GetDataReeUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent>(HomeViewState.ShowProgress(true)) {
    init {
        getData()
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.RefreshData -> getData()
        }
    }

    private fun getData() {
        launch {
            updateViewState(HomeViewState.ShowProgress(true))
            getData(UseCase.None).fold(
                onSuccess = {
                    updateViewState(HomeViewState.ShowData(it.toString()))
                },
                onFailure = {
                    //updateViewState(HomeViewState(false, "", it.message ?: ""))
                }
            )
        }

    }
}