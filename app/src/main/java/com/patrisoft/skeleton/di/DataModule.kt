package com.patrisoft.skeleton.di

import com.patrisoft.data.remote.datasource.api.RepositoryApi
import com.patrisoft.data.remote.repository.TestRepositoryImpl
import com.patrisoft.domain.repository.TestRepository
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
    fun providerRepository(rickAndMortyApi: RepositoryApi): TestRepository =
        TestRepositoryImpl(rickAndMortyApi)


    @Provides
    @Singleton
    fun providerApi(): RepositoryApi =
        RepositoryApi()


}