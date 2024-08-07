package com.example.data.di

import com.example.data.datasource.local.AuthLocalDataSource
import com.example.data.di.annotations.MainServiceRetrofit
import com.example.data.di.annotations.OntoLLMRetrofit
import com.example.data.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authLocalDataSource: AuthLocalDataSource,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(name = "Authorization", value = "Bearer ${authLocalDataSource.getAccessToken().orEmpty()}")
                    .build()

                return@addInterceptor chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
            .callTimeout(5*60, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(5*60, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(5*60, java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideJsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }


    @MainServiceRetrofit
    @Provides
    @Singleton
    fun provideMainRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @OntoLLMRetrofit
    @Provides
    @Singleton
    fun provideOntoLLMRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_OCR)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

}