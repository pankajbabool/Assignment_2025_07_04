package com.example.assignment.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDate(): LocalDateTime {
    val instant = Clock.System.now()
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return localDateTime
}
fun getCurrentYear(): Int {
    val today = getCurrentDate()
    val year = today.year
    return year
}

