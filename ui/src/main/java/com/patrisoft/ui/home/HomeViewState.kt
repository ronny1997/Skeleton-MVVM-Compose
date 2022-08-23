package com.patrisoft.ui.home

import com.patrisoft.core.base.ViewState

sealed class HomeViewState : ViewState {
    class ShowProgress(val showLoading: Boolean) : HomeViewState()
    class ShowData(val text: String) : HomeViewState()
    class ShowError(val text: String) : HomeViewState()
}