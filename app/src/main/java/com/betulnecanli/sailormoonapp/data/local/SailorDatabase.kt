package com.betulnecanli.sailormoonapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.betulnecanli.sailormoonapp.data.local.dao.SailorDao
import com.betulnecanli.sailormoonapp.data.local.dao.SailorRemoteKeyDao
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.model.SailorRemoteKey
import com.betulnecanli.sailormoonapp.utils.DatabaseConverter

@Database(entities = [SailorMoon::class, SailorRemoteKey::class], version = 3, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class SailorDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): SailorDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, SailorDatabase::class.java)
            } else {
                Room.databaseBuilder(context, SailorDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    abstract fun sailorDao() : SailorDao
    abstract fun remoteKeyDao() : SailorRemoteKeyDao
}