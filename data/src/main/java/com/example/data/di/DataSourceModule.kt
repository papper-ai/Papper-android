package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.datasource.ChatRemoteDataSource
import com.example.data.datasource.StorageRemoteDataSource
import com.example.data.repository.AccountRepositoryImpl
import com.example.data.repository.ChatRepositoryImpl
import com.example.data.repository.StorageRepositoryImpl
import com.example.data.service.AuthService
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.ChatRepository
import com.example.domain.repository.StorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideChatRemoteDataSource(apiService: ApiService): ChatRemoteDataSource {
        return ChatRemoteDataSource(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideStorageRemoteDataSource(apiService: ApiService): StorageRemoteDataSource {
        return StorageRemoteDataSource(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return AuthService()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AccountRepository {
        return AccountRepositoryImpl(service = authService)
    }

    @Provides
    @Singleton
    fun provideChatRepository(chatRemoteDataSource: ChatRemoteDataSource): ChatRepository {
        return ChatRepositoryImpl(chatRemoteDataSource = chatRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideStorageRepository(storageRemoteDataSource: StorageRemoteDataSource): StorageRepository {
        return StorageRepositoryImpl(storageDataSource = storageRemoteDataSource)
    }

}