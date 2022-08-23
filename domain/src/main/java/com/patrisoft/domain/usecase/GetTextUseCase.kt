package com.patrisoft.domain.usecase

import com.patrisoft.core.domain.UseCase
import com.patrisoft.domain.repository.TestRepository
import javax.inject.Inject

class GetTextUseCase @Inject constructor(
    private val repository: TestRepository
) : UseCase<String, Boolean>() {
    override suspend fun run(params: Boolean): String {
        return repository.getText()
    }
}
