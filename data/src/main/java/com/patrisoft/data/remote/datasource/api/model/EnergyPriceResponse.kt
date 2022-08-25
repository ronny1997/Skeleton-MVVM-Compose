package com.patrisoft.data.remote.datasource.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EnergyPriceResponse(
    @SerialName("data")
    val data: Data,
    @SerialName("included")
    val included: List<Included>
)