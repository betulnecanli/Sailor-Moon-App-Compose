package com.betulnecanli.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.betulnecanli.data.local.dao.SailorDao
import com.betulnecanli.domain.model.Characters
import com.betulnecanli.domain.model.SailorRemoteKey
import com.betulnecanli.sailormoonapp.utils.DatabaseConverter

@Database(entities = [Characters::class, SailorRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class SailorDatabase : RoomDatabase() {
    abstract fun sailorDao() : SailorDao
    abstract fun remoteKeyDao() : SailorRemoteKey
}