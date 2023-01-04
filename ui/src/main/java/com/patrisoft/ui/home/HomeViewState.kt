package com.patrisoft.ui.home

import com.patrisoft.core.base.ViewState
import com.patrisoft.domain.model.EnergyPriceDto

sealed class HomeViewState : ViewState {
    class ShowProgress(val energyPriceDto: EnergyPriceDto? = null) : HomeViewState()
    class ShowData(val dataEnergy: DataEnergy) : HomeViewState()
    class ShowError(val text: String) : HomeViewState()
}