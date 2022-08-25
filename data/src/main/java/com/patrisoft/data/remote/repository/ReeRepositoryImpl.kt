package com.patrisoft.data.remote.repository

import com.patrisoft.core.utils.dateNowEnd
import com.patrisoft.core.utils.dateNowStart
import com.patrisoft.data.remote.datasource.api.ReeApi
import com.patrisoft.data.remote.repository.mapper.toDomain
import com.patrisoft.domain.model.EnergyPriceDto
import com.patrisoft.domain.repository.ReeRepository
import javax.inject.Inject

class ReeRepositoryImpl @Inject constructor(
    private val api: ReeApi
) : ReeRepository {
    override suspend fun getData(): EnergyPriceDto {
        val start = dateNowStart()
        val end = dateNowEnd()

        return api.getData(start, end).toDomain()
    }
}