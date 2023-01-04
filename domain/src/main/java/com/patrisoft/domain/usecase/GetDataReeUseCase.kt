package com.patrisoft.domain.usecase

import com.patrisoft.core.domain.UseCase
import com.patrisoft.domain.model.EnergyPriceDto
import com.patrisoft.domain.repository.ReeRepository
import javax.inject.Inject

class GetDataReeUseCase @Inject constructor(
    private val repository: ReeRepository
) : UseCase<EnergyPriceDto, GetDataReeUseCase.ParamsGetData>() {
    override suspend fun run(params: ParamsGetData): EnergyPriceDto {
        return repository.getData(params)
    }
    data class ParamsGetData(
    val start:String,
    val end:String)
}
