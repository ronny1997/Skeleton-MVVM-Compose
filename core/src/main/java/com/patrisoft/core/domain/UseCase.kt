package com.patrisoft.core.domain

import com.patrisoft.core.dispacher.io
import com.patrisoft.core.domain.error.Error
import kotlinx.coroutines.withContext

abstract class UseCase<Type, in Params> where Type : Any {

    protected abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Result<Type> {
        return withContext(io) {
            try {
                successOf(run(params))
            } catch (e: Exception) {
                failureOf(Error.Generic(e.message, e.stackTraceToString()))
            }
        }
    }

    object None
}