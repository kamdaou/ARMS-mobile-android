package com.amalitech.domain.utils

sealed class TypedResponse<out T>(val data: T? = null, val message: UiText? = null) {
    class Success<out T>(data: T?) : TypedResponse<T>(data)
    class Error<out T>(message: UiText, data: T? = null) : TypedResponse<T>(data, message)
    class Loading<T>(data: T? = null) : TypedResponse<T>(data)
}

