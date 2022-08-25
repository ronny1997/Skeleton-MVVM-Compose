package com.patrisoft.data.remote.datasource.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String
)