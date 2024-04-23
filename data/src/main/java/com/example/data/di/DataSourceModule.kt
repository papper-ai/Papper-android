package com.example.data.di

import com.example.data.api.AuthApiService
import com.example.data.api.ChatApiService
import com.example.data.api.StorageApiService
import com.example.data.datasource.local.AuthLocalDataSource
import com.example.data.datasource.remote.ChatRemoteDataSource
import com.example.data.datasource.remote.StorageRemoteDataSource
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
    fun provideChatRemoteDataSource(apiService: ChatApiService): ChatRemoteDataSource {
        return ChatRemoteDataSource(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideStorageRemoteDataSource(apiService: StorageApiService): StorageRemoteDataSource {
        return StorageRemoteDataSource(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideAuthService(apiService: AuthApiService, authLocalDataSource: AuthLocalDataSource): AuthService {
        return AuthService(apiService = apiService, authLocalDataSource = authLocalDataSource)
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