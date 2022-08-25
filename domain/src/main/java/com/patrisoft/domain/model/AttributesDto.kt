package com.patrisoft.domain.model

import kotlinx.datetime.LocalDateTime

data class AttributesDto(
    val color: String,
    val lastUpdate: LocalDateTime,
    val title: String,
    val values: List<HourEnergyPriceDto>,
    val minimum: Double,
    val medium: Double,
    val maximum: Double
)