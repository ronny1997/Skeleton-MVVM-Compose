package com.patrisoft.data.remote.datasource.api

import com.patrisoft.core.data.KtorApiClient
import com.patrisoft.data.remote.datasource.api.model.EnergyPriceResponse
import kotlinx.datetime.LocalDateTime

import javax.inject.Inject

class ReeApi @Inject constructor(private val apiClient: KtorApiClient) {

    suspend fun getData(dateInit: String, dateEnd: String) =
        apiClient.get<EnergyPriceResponse>("precios-mercados-tiempo-real?start_date=${dateInit}&end_date=$dateEnd&time_trunc=hour")

}