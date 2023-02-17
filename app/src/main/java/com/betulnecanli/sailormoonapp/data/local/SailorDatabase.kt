package com.betulnecanli.sailormoonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.betulnecanli.sailormoonapp.data.local.dao.SailorDao
import com.betulnecanli.sailormoonapp.data.local.dao.SailorRemoteKeyDao
import com.betulnecanli.sailormoonapp.domain.model.Characters
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.model.SailorRemoteKey
import com.betulnecanli.sailormoonapp.utils.DatabaseConverter

@Database(entities = [SailorMoon::class, SailorRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class SailorDatabase : RoomDatabase() {
    abstract fun sailorDao() : SailorDao
    abstract fun remoteKeyDao() : SailorRemoteKeyDao
}