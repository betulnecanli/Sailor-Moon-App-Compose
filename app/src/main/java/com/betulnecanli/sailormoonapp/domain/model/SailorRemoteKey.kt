package com.betulnecanli.sailormoonapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants.SAILOR_REMOTE_KEY_DATABASE_TABLE


@Entity(tableName = SAILOR_REMOTE_KEY_DATABASE_TABLE)
data class SailorRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
