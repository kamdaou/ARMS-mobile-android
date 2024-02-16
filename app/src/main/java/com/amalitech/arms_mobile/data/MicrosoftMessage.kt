package com.amalitech.arms_mobile.data

data class AuthenticationException(
    val message: String,
    val cause: Throwable? = null
) {
    constructor(cause: Throwable) : this(
        message = cause.message ?: "An error occurred during authentication.", cause = cause
    )
    override fun toString(): String {
        return "AuthenticationException(message='$message', cause=$cause)"
    }
}

data class GraphApiResponse(val message: String)
data class GraphApiResponseError(val message: String)