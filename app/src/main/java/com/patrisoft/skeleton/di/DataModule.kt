package com.patrisoft.skeleton.di

import com.patrisoft.core.data.KtorApiClient
import com.patrisoft.data.remote.datasource.api.ReeApi
import com.patrisoft.data.remote.repository.ReeRepositoryImpl
import com.patrisoft.domain.repository.ReeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providerRepository(reeApi: ReeApi): ReeRepository =
        ReeRepositoryImpl(reeApi)


    @Provides
    @Singleton
    fun providerApi(ktorApiClient: KtorApiClient): ReeApi =
        ReeApi(ktorApiClient)


}