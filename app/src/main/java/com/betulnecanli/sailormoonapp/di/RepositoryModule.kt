package com.betulnecanli.sailormoonapp.di

import android.content.Context
import com.betulnecanli.sailormoonapp.data.pref.DataStoreOperationsImpl
import com.betulnecanli.sailormoonapp.domain.repository.DatastoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context) : DatastoreOperations{
        return DataStoreOperationsImpl(context = context)
    }


}