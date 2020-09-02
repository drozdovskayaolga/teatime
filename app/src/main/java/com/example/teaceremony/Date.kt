package com.example.teaceremony

import org.threeten.bp.Duration

fun formatDuration(millis: Long): String {
    val amountOfTime = Duration.ofMillis(millis)
    val hours: Long = amountOfTime.toHours()
    val mins: Long = amountOfTime.minusHours(hours).toMinutes()
    val seconds: Long = amountOfTime.minusHours(hours).minusMinutes(mins).seconds
    return if (amountOfTime.toHours() > 0) {
        String.format("%02d:%02d:%02d", hours, mins, seconds)
    } else {
        String.format("%02d:%02d", mins, seconds)
    }
}