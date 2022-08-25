package com.patrisoft.data.remote.datasource.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourEnergyPrice(
    @SerialName("datetime")
    val datetime: String,
    @SerialName("percentage")
    val percentage: Double,
    @SerialName("value")
    val value: Double
)