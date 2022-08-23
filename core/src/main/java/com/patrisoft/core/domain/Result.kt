package com.patrisoft.core.domain

import com.patrisoft.core.domain.error.Error

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure<out T>(val error: Error) : Result<T>()
}

fun <T> successOf(value: T) = Result.Success(value)
fun <T> failureOf(error: Error) = Result.Failure<T>(error)

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Error) -> R
): R = when (this) {
    is Result.Success -> onSuccess(value)
    is Result.Failure -> onFailure(error)
}