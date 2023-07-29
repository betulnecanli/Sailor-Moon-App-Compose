package com.betulnecanli.sailormoonapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants.SAILOR_DATABASE_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = SAILOR_DATABASE_TABLE)
data class SailorMoon(
    val age: Int?,
    val birthday: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val name: String,
    val realName: String,
    val species: String,
    val about: String,
    val heartRate: Double?
)
