package com.patrisoft.core.domain.error


sealed class Error(override val message: String?) : Throwable(message) {

    sealed class Repository(message: String? = null) : Error(message) {
        class Http(val code: Int, message: String?) : Repository(message) {
            override val message: String
                get() = "Http error code: $code - Message: ${super.message}"
        }

        object Unauthorized : Repository()
        object Forbidden : Repository()
    }

    class Generic(message: String?, val stackTrace: String? = null) : Error(message)
}

sealed class ApiError(override val message: String) : Error(message) {
    class FieldError(val name: String, val code: String, override val message: String) :
        ApiError(message)

    class MessageError(override val message: String) : ApiError(message)
}
