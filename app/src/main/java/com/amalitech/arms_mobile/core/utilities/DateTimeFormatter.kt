package com.amalitech.arms_mobile.core.utilities

import java.time.Clock


object DateTimeFormatter {
    fun getParsedDate(date: String?): String? {
        if(date == null) return null

        val dateValues = date.split("-")
        if(dateValues.size >= 2) {
            var (year, month) = dateValues

            year = year.substring(2)
            month = when(month.toInt()) {
                1 -> "Jan"
                2 -> "Feb"
                3 -> "Mar"
                4 -> "Apr"
                5 -> "May"
                6 -> "Jun"
                7 -> "Jul"
                8 -> "Aug"
                9 -> "Sep"
                10 -> "Oct"
                11-> "Nov"
                else-> "Dec"
            }

            return "$month $year"
        } else {
            return null
        }
    }

    fun anniversary(date: String): String {
        val (year, _) = date.split(Regex("[-T:.]"))

        val dateTime = Clock.system(Clock.systemDefaultZone().zone).instant()
        val currentYear = dateTime.toString().split("-").first().toInt()
        var yearDiff = currentYear - year.toInt()

        yearDiff = if(yearDiff == 0) 1 else yearDiff

        return when(yearDiff.toString()[0]) {
            '1' -> "${yearDiff}st"
            '2' -> "${yearDiff}nd"
            '3' -> "${yearDiff}rd"
            else -> "${yearDiff}th"
        }
    }
}
