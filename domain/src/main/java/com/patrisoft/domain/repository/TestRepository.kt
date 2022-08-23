package com.patrisoft.domain.repository

interface TestRepository {
    suspend fun getText(): String
}