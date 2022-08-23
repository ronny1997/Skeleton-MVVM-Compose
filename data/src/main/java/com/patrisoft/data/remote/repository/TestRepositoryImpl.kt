package com.patrisoft.data.remote.repository

import com.patrisoft.data.remote.datasource.api.RepositoryApi
import com.patrisoft.domain.repository.TestRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val api: RepositoryApi
) : TestRepository {
    override suspend fun getText(): String {
        Thread.sleep(1000)
        return api.getCharacters()
    }
}