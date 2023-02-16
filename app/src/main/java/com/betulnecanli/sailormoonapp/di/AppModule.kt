package com.betulnecanli.sailormoonapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betulnecanli.sailormoonapp.data.local.SailorDatabase
import com.betulnecanli.sailormoonapp.utils.Constants.SAILOR_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context : Context
    ) :RoomDatabase{
        return Room.databaseBuilder(
            context,
            SailorDatabase::class.java,
            SAILOR_DB
        ).build()
    }

}