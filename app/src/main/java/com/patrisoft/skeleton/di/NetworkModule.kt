package com.patrisoft.skeleton.di

import co.touchlab.kermit.Kermit
import com.patrisoft.skeleton.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun kermit(): Kermit = Kermit()

    @Provides
    @Singleton
    fun createJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
        encodeDefaults = true
        useAlternativeNames = false
    }

    @Provides
    @Singleton
    fun buildHttpClient(json: Json, log: Kermit) = HttpClient {
        defaultRequest {
            host = "apidatos.ree.es/es/datos/mercados"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        if (BuildConfig.DEBUG) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        log.v("Ktor") { message }
                    }
                }
                level = LogLevel.ALL
            }
        }
    }


}