package com.amalitech.arms_mobile.core.utilities

data class User(
    val givenName: String,
     val mail: String,
){
    companion object {
        fun fromJSON(json: org.json.JSONObject) : User {
            val givenName = json.getString("givenName")
            val mail = json.getString("mail")
            return User(givenName, mail)
        }
    }
}
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

data class GraphApiResponseError(val message: String)

