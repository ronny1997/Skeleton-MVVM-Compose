package com.patrisoft.core.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.util.*
import javax.inject.Inject

class KtorApiClient @Inject constructor(val apiClient: HttpClient) {

    suspend inline fun <reified T> get(path: String, params: StringValues = StringValues.Empty): T {
        return apiClient.get {
            url {
                encodedPath = path
                parameters.appendAll(params)
            }
        }
    }

}