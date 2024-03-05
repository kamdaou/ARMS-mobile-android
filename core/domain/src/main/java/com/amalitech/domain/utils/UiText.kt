package com.amalitech.domain.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class StringResource(@StringRes val id: Int) : UiText()
    data class DynamicString(val text: String) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> this.text
            is StringResource -> context.getString(this.id)
        }
    }
}
