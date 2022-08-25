package com.patrisoft.data.remote.datasource.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    @SerialName("color")
    val color: String,
    @SerialName("last-update")
    val lastUpdate: String,
    @SerialName("magnitude")
    val magnitude: String,
    @SerialName("title")
    val title: String,
    @SerialName("values")
    val values: List<HourEnergyPrice>
)