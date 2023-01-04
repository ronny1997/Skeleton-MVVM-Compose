package com.patrisoft.domain.repository

import com.patrisoft.domain.model.EnergyPriceDto
import com.patrisoft.domain.usecase.GetDataReeUseCase

interface ReeRepository {
    suspend fun getData(params: GetDataReeUseCase.ParamsGetData): EnergyPriceDto

}