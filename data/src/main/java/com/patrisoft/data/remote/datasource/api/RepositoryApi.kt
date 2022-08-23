package com.patrisoft.data.remote.datasource.api

import javax.inject.Inject
import kotlin.random.Random

class RepositoryApi @Inject constructor() {

    suspend fun getCharacters(): String = Random.nextInt(0, 100).toString()

}
//viernes o lunes del 5 de octubre