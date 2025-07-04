package com.example.assignment.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

fun convertMillisToDateAsDDMMYYYY(millis: Long): String {
    val instant = Instant.fromEpochMilliseconds(millis)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val day = dateTime.dayOfMonth.toString().padStart(2, '0')
    val month = dateTime.monthNumber.toString().padStart(2, '0')
    val year = dateTime.year
    val output = "$day/$month/$year"
    return output
}

fun getStartOfTodayMillis(): Long {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.UTC)
        .date.atStartOfDayIn(TimeZone.UTC)
        .toEpochMilliseconds()
}
