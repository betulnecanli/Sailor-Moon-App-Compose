package com.betulnecanli.sailormoonapp.di

import android.content.Context
import com.betulnecanli.sailormoonapp.data.local.SailorDatabase
import com.betulnecanli.sailormoonapp.data.remote.SailorApi
import com.betulnecanli.sailormoonapp.data.repository.RemoteDataSourceImpl
import com.betulnecanli.sailormoonapp.domain.repository.RemoteDataSource
import com.betulnecanli.sailormoonapp.utils.Constants.BASE_URL
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideHttpClient(context : Context): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit{
       val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }


    @Provides
    @Singleton
    fun providesSailorApi(retrofit: Retrofit) : SailorApi{
        return retrofit.create(SailorApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(
    sailorApi : SailorApi,
    sailorDB: SailorDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImpl(
        sailorApi, sailorDB
        )
    }

}