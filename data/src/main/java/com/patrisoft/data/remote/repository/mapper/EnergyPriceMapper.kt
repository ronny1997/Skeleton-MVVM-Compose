package com.patrisoft.data.remote.repository.mapper

import com.patrisoft.core.utils.fiveDecimalUtils
import com.patrisoft.data.remote.datasource.api.model.*
import com.patrisoft.domain.model.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.util.*


fun EnergyPriceResponse.toDomain(): EnergyPriceDto =
    EnergyPriceDto(data.toDomain(), included.map { it.toDomain() })

fun Data.toDomain(): DataDto = DataDto( id, type)

fun Included.toDomain(): IncludedDto = IncludedDto(attributes.toDomain(), id, type)

fun Attributes.toDomain(): AttributesDto =
    AttributesDto(
        color,
        lastUpdate.toInstant().toLocalDateTime(TimeZone.currentSystemDefault()),
        title,
        values.map { it.toDomain() },
        min(),
        average(),
        max()
    )

fun HourEnergyPrice.toDomain(): HourEnergyPriceDto =
    HourEnergyPriceDto(
        datetime.toInstant().toLocalDateTime(TimeZone.currentSystemDefault()),
        percentage,
        valueMwhToKwh(),
        colorEnergy(),
        actualHour()
    )

fun HourEnergyPrice.colorEnergy(): String =
    when (datetime.toInstant().toLocalDateTime(TimeZone.currentSystemDefault()).hour) {
        HOUR_0 -> COLOR_RED
        HOUR_1 -> COLOR_RED
        HOUR_2 -> COLOR_RED
        HOUR_3 -> COLOR_RED
        HOUR_4 -> COLOR_RED
        HOUR_5 -> COLOR_RED
        HOUR_6 -> COLOR_RED
        HOUR_7 -> COLOR_RED
        HOUR_8 -> COLOR_RED
        HOUR_9 -> COLOR_ORANGE
        HOUR_10 -> COLOR_ORANGE
        HOUR_11 -> COLOR_GREEN
        HOUR_12 -> COLOR_GREEN
        HOUR_13 -> COLOR_GREEN
        HOUR_14 -> COLOR_GREEN
        HOUR_15 -> COLOR_GREEN
        HOUR_16 -> COLOR_GREEN
        HOUR_17 -> COLOR_GREEN
        HOUR_18 -> COLOR_GREEN
        HOUR_19 -> COLOR_ORANGE
        HOUR_20 -> COLOR_RED
        HOUR_21 -> COLOR_RED
        HOUR_22 -> COLOR_ORANGE
        HOUR_23 -> COLOR_ORANGE
        else -> COLOR_GREEN
    }

fun HourEnergyPrice.actualHour(): Boolean  {
    val calendar = Calendar.getInstance()
    calendar.get(Calendar.HOUR_OF_DAY)
    val date = datetime.toInstant().toLocalDateTime(TimeZone.currentSystemDefault())
    return calendar.time.hours == date.hour
}

fun HourEnergyPrice.valueMwhToKwh(): Double {
    val kwh = value / 1000
    return kwh.fiveDecimalUtils()

}

fun Attributes.min(): Double {
    return values.minOfOrNull { (it.value/1000).fiveDecimalUtils() } ?: 0.0
}

fun Attributes.average(): Double {
    val userNumbers = mutableListOf<Double>()
    userNumbers.addAll(values.map { (it.value/1000) })
    return userNumbers.average().fiveDecimalUtils()
}

fun Attributes.max(): Double {
    return values.maxOfOrNull { (it.value/1000).fiveDecimalUtils() } ?: 0.0
}

const val HOUR_0 = 0
const val HOUR_1 = 1
const val HOUR_2 = 2
const val HOUR_3 = 3
const val HOUR_4 = 4
const val HOUR_5 = 5
const val HOUR_6 = 6
const val HOUR_7 = 7
const val HOUR_8 = 8
const val HOUR_9 = 9
const val HOUR_10 = 10
const val HOUR_11 = 11
const val HOUR_12 = 12
const val HOUR_13 = 13
const val HOUR_14 = 14
const val HOUR_15 = 15
const val HOUR_16 = 16
const val HOUR_17 = 17
const val HOUR_18 = 18
const val HOUR_19 = 19
const val HOUR_20 = 20
const val HOUR_21 = 21
const val HOUR_22 = 22
const val HOUR_23 = 23


const val COLOR_RED = "#8Cf23b25"
const val COLOR_GREEN = "#8C0faf0f"
const val COLOR_ORANGE = "#8Cf4a420"