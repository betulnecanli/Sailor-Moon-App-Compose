package com.betulnecanli.sailormoonapp.di

import android.content.Context
import com.betulnecanli.sailormoonapp.data.repository.DataStoreOperationsImpl
import com.betulnecanli.sailormoonapp.data.repository.Repository
import com.betulnecanli.sailormoonapp.domain.repository.DatastoreOperations
import com.betulnecanli.sailormoonapp.domain.use_cases.UseCases
import com.betulnecanli.sailormoonapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.betulnecanli.sailormoonapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
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

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }


}