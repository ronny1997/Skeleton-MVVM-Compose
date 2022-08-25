package com.patrisoft.domain.model

import kotlinx.datetime.LocalDateTime


data class HourEnergyPriceDto(
    val datetime: LocalDateTime,
    val percentage: Double,
    val value: Double,
    val color: String,
    val actualHour: Boolean
)