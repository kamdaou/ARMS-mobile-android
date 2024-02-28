package com.amalitech.arms_mobile.core.utilities

object StringFormatter {

    class Name(val name: String) {
        fun initials(): String {
            val names = name.split(" ")

            val first = names.firstOrNull()
            val last = names.getOrNull(1)

            return first?.elementAtOrNull(0)?.uppercase() + last?.elementAtOrNull(0)?.uppercase()
        }

        fun parse(shorten: Boolean = false): String {
            if (!shorten) {
                return name
            }

            val names = name.split(" ")

            val first = names.firstOrNull()
            val last = names.lastOrNull()

            return "$first $last"
        }
    }

}