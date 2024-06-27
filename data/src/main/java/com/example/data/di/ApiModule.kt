package com.example.data.di

import com.example.data.api.AuthApiService
import com.example.data.api.ChatApiService
import com.example.data.api.LLMApiService
import com.example.data.api.StorageApiService
import com.example.data.di.annotations.MainServiceRetrofit
import com.example.data.di.annotations.OntoLLMRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthApiService(@MainServiceRetrofit retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideChatApiService(@MainServiceRetrofit retrofit: Retrofit): ChatApiService =
        retrofit.create(ChatApiService::class.java)

    @Provides
    @Singleton
    fun provideStorageApiService(@MainServiceRetrofit retrofit: Retrofit): StorageApiService =
        retrofit.create(StorageApiService::class.java)

    @Provides
    @Singleton
    fun provideLLMApiService(@OntoLLMRetrofit retrofit: Retrofit): LLMApiService =
        retrofit.create(LLMApiService::class.java)

}