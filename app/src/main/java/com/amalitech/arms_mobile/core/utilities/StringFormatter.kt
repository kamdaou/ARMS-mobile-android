package com.amalitech.arms_mobile.core.utilities

import android.util.Log

object StringFormatter {

    class Name(val name: String) {
        fun initials(): String {
            val names = name.split(" ")

            val first = names.first()
            val last = names[1]

            return first.elementAt(0).uppercase() + last.elementAt(0).uppercase()
        }

        fun parse(shorten: Boolean = false): String {
            if(!shorten) {
                return name
            }

            val names = name.split(" ")

            val first = names.first()
            val last = names.last()

            return "$first $last"
        }
    }

}