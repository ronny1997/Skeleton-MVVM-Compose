package com.patrisoft.domain.model

import kotlinx.datetime.LocalDateTime

data class InfoDataDto(
    val lastUpdate: LocalDateTime,
    val title: String
)