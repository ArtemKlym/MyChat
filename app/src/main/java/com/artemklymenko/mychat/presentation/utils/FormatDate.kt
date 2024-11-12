package com.artemklymenko.mychat.presentation.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun formatDate(date: Date): String {
    val currentTime = Date().time
    val timeDifference = currentTime - date.time

    if (timeDifference < TimeUnit.HOURS.toMillis(24)) {
        val hourMinuteFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return hourMinuteFormat.format(date)
    }

    if (timeDifference < TimeUnit.DAYS.toMillis(7)) {
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        return dayFormat.format(date)
    }

    val calendarDate = Calendar.getInstance().apply { time = date }
    val calendarNow = Calendar.getInstance()
    if (calendarDate.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)) {
        val monthDayFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        return monthDayFormat.format(date)
    }

    val fullDateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    return fullDateFormat.format(date)
}