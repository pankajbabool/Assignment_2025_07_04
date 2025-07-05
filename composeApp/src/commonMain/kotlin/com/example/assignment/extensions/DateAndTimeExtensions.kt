package com.example.assignment.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
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

//2025-07-04T16:45 -> 04 Jul, 10:15 PM
fun formatLocalDateTime(iso: String): String {
    val localDateTime = LocalDateTime.parse(iso)
        .toInstant(TimeZone.UTC)
        .toLocalDateTime(TimeZone.currentSystemDefault())

    val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
    val month = localDateTime.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }

    val hour24 = localDateTime.hour
    val hour12 = (hour24 % 12).let { if (it == 0) 12 else it }
    val minute = localDateTime.minute.toString().padStart(2, '0')
    val amPm = if (hour24 < 12) "AM" else "PM"

    val formattedTime = "$day $month, $hour12:$minute $amPm"
    println("Formatted Time: $iso -> $formattedTime")

    return formattedTime
}