package com.patrisoft.ui.home.utils

import com.patrisoft.domain.model.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun buildDataRee() = EnergyPriceDto(
    buildData(),
    buildIncludeList()
)

fun buildData() = DataDto("mer13", "Precios mercado peninsular en tiempo real")

fun buildIncludeList() = listOf(buildInclude())
fun buildInclude() = IncludedDto(buildAttributes(), "1001", "PVPC (€/MWh)")
fun buildAttributes() = AttributesDto(
    "#ffcf09",
    "2022-08-22T20:17:29.000+02:00".toInstant().toLocalDateTime(TimeZone.currentSystemDefault()),
    "price",
    buildValuesList(),
    0.500,
    0.500,
    0.500
)

fun buildValuesList() = listOf(
    buildHourEnergyPrice(),
    buildHourEnergyPriceTwo(),
    buildHourEnergyPriceThree()
)

fun buildHourEnergyPrice() = HourEnergyPriceDto(
    "2022-08-23T00:00:00.000+02:00".toInstant().toLocalDateTime(TimeZone.currentSystemDefault()),
    0.7098124858799608,
    471.28,
    "#8Cf4a420",
    false
)

fun buildHourEnergyPriceTwo() = HourEnergyPriceDto(
    "2022-08-23T01:00:00.000+02:00".toInstant().toLocalDateTime(TimeZone.currentSystemDefault()),
    0.7275859381089718,
    466.71,
    "#8C0faf0f",
    true
)

fun buildHourEnergyPriceThree() = HourEnergyPriceDto(
    "2022-08-23T02:00:00.000+02:00".toInstant().toLocalDateTime(TimeZone.currentSystemDefault()),
    0.7502954836776481,
    450.71,
    "#8Cf23b25",
    false
)