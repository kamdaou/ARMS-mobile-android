package com.amalitech.arms_mobile.core.utilities

object StringFormatter {

    class Name(val name: String) {
        fun initials(): String {
            val names = name.split(" ")

            val first = names.first()
            val last = names.last()

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