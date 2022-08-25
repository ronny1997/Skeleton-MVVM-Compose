package com.patrisoft.data

import com.patrisoft.core.utils.dateNowEnd
import com.patrisoft.core.utils.dateNowStart
import com.patrisoft.data.remote.datasource.api.ReeApi
import com.patrisoft.data.remote.repository.ReeRepositoryImpl
import com.patrisoft.data.remote.repository.mapper.COLOR_RED
import com.patrisoft.data.utils.TestUtils
import com.patrisoft.data.utils.buildDataRee
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.*

class ReeRepositoryImplTest : TestUtils() {

    @MockK
    private lateinit var api: ReeApi

    @InjectMockKs
    private lateinit var repository: ReeRepositoryImpl

    @Test
    fun `Get data when call to api should map to domain`() = runBlocking {
        val start = dateNowStart()
        val end = dateNowEnd()
        coEvery { api.getData(start, end)} returns buildDataRee()
        val data = repository.getData()

        //Data
        val dataResponse = data.data
        dataResponse.id shouldBe "mer13"
        dataResponse.type shouldBe "Precios mercado peninsular en tiempo real"

        //include
        val include = data.included[0]
        include.id shouldBe "1001"
        include.type shouldBe "PVPC (€/MWh)"
        val attributes = include.attributes
        attributes.lastUpdate.toString() shouldBe "2022-08-22T20:17:29"
        attributes.color shouldBe "#ffcf09"
        attributes.title shouldBe "PVPC (€/MWh)"
        attributes.minimum shouldBe 450.71
        attributes.medium shouldBe 462.91
        attributes.maximum shouldBe 471.28
        val hourEnergyPriceDtoOne = attributes.values[0]
        val hourEnergyPriceDtoTwo = attributes.values[1]
        val hourEnergyPriceDtoThree = attributes.values[2]

        hourEnergyPriceDtoOne.datetime.toString() shouldBe  "2022-08-23T00:00"
        hourEnergyPriceDtoOne.percentage shouldBe  0.7098124858799608
        hourEnergyPriceDtoOne.value shouldBe  0.47128
        hourEnergyPriceDtoOne.color shouldBe  COLOR_RED
        val calendar = Calendar.getInstance()
        calendar.get(Calendar.HOUR_OF_DAY)
        val datetime =  hourEnergyPriceDtoOne.datetime
        val isHour = calendar.time.hours == datetime.hour
        hourEnergyPriceDtoOne.actualHour shouldBe isHour

        hourEnergyPriceDtoTwo.datetime.toString() shouldBe  "2022-08-23T01:00"
        hourEnergyPriceDtoTwo.percentage shouldBe  0.7275859381089718
        hourEnergyPriceDtoTwo.value shouldBe  0.46671
        hourEnergyPriceDtoOne.color shouldBe COLOR_RED

        hourEnergyPriceDtoThree.datetime.toString() shouldBe  "2022-08-23T02:00"
        hourEnergyPriceDtoThree.percentage shouldBe  0.7502954836776481
        hourEnergyPriceDtoThree.value shouldBe  0.45071
        hourEnergyPriceDtoOne.color shouldBe  COLOR_RED

    }

}