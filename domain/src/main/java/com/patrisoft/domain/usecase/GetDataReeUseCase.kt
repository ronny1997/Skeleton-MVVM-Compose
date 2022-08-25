package com.patrisoft.domain.usecase

import com.patrisoft.core.domain.UseCase
import com.patrisoft.domain.model.EnergyPriceDto
import com.patrisoft.domain.repository.ReeRepository
import javax.inject.Inject

class GetDataReeUseCase @Inject constructor(
    private val repository: ReeRepository
) : UseCase<EnergyPriceDto, UseCase.None>() {
    override suspend fun run(params: None): EnergyPriceDto {
        return repository.getData()
    }
}
