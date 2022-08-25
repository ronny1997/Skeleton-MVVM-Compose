package com.patrisoft.domain.repository

import com.patrisoft.domain.model.EnergyPriceDto

interface ReeRepository {
    suspend fun getData(): EnergyPriceDto
}