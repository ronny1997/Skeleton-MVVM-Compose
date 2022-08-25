package com.patrisoft.domain.model

data class EnergyPriceDto(
    val data: DataDto,
    val included: List<IncludedDto>
)