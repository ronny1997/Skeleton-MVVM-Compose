package com.patrisoft.ui.home

import com.patrisoft.core.base.BaseViewModel
import com.patrisoft.core.domain.fold
import com.patrisoft.core.utils.dateMorningEnd
import com.patrisoft.core.utils.dateMorningStart
import com.patrisoft.core.utils.dateNowEnd
import com.patrisoft.core.utils.dateNowStart
import com.patrisoft.domain.model.EnergyPriceDto
import com.patrisoft.domain.usecase.GetDataReeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getData: GetDataReeUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent>(HomeViewState.ShowProgress()) {
    private var dataEnergy: DataEnergy = DataEnergy()

    init {
        getData()
    }

    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.Now -> getData()
            is HomeViewEvent.Morning -> getMorningData()
        }
    }

    private fun getData() {
        updateViewState(HomeViewState.ShowData(dataEnergy.copy(showProgress = true)))
        launch {
            val getDataNowParams = GetDataReeUseCase.ParamsGetData(
                dateNowStart(),
                dateNowEnd()
            )
            getData(getDataNowParams).fold(
                onSuccess = {
                    dataEnergy = DataEnergy(it, false)
                    updateViewState(HomeViewState.ShowData(dataEnergy))
                },
                onFailure = {
                    //updateViewState(HomeViewState(false, "", it.message ?: ""))
                }
            )
        }
    }

    private fun getMorningData() {
        launch {
            val getDataMorningParams = GetDataReeUseCase.ParamsGetData(
                dateMorningStart(),
                dateMorningEnd()
            )
            updateViewState(HomeViewState.ShowData(dataEnergy.copy(showProgress = true)))
            getData(getDataMorningParams).fold(
                onSuccess = {
                    dataEnergy = dataEnergy.copy(energyPriceDto = it, showProgress = false)
                    updateViewState(HomeViewState.ShowData(dataEnergy))
                },
                onFailure = {
                    //updateViewState(HomeViewState(false, "", it.message ?: ""))
                }
            )
        }
    }
}

data class DataEnergy(
    val energyPriceDto: EnergyPriceDto? = null,
    val showProgress: Boolean = false
)