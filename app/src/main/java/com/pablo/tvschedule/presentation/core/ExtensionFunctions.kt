package com.pablo.tvschedule.presentation.core

import java.time.LocalDate

fun String.fromHour(): Int {
    val times = this.split(":")
        .map { it.toInt() }
    return times[0] * 100 + times[1]
}

fun Int.toHour() = buildString {
    append((this@toHour / 100).toString().padStart(2, '0'))
    append(":")
    append((this@toHour % 100).toString().padStart(2, '0'))
}


fun LocalDate.formatDate(): String {
    return buildString {
        append(this@formatDate.year)
        append("-")
        append(this@formatDate.monthValue.toString().padStart(2, '0'))
        append("-")
        append(this@formatDate.dayOfMonth.toString().padStart(2, '0'))
    }
}

fun LocalDate.usFormatDate(): String {
    return buildString {
        append(this@usFormatDate.month)
        append(" ")
        append(this@usFormatDate.dayOfMonth)
        append(", ")
        append(this@usFormatDate.year)
    }
}