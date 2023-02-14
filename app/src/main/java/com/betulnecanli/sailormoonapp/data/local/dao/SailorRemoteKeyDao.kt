package com.betulnecanli.sailormoonapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.betulnecanli.sailormoonapp.domain.model.SailorRemoteKey

@Dao
interface SailorRemoteKeyDao{

    @Query("SELECT * FROM sailor_remote_key_table WHERE id = :id")
    suspend fun getRemoteKey(id : Int): SailorRemoteKey?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(sailorRemoteKey: List<SailorRemoteKey>)

    @Query("DELETE FROM sailor_remote_key_table")
    suspend fun deleteAllRemoteKeys()

}