package com.amalitech.arms_mobile.core.utilities

object DateTimeFormatter {
    fun getParsedDate(date: String?): Array<String>? {
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

            return arrayOf(month, year)
        } else {
            return null
        }
    }
}
