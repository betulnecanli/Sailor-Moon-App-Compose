package com.betulnecanli.sailormoonapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants.SAILOR_DATABASE_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = SAILOR_DATABASE_TABLE)
data class SailorMoon(
    @SerializedName("age")
    val age: Int,
    @SerializedName("birthday")
    val birthday: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("realName")
    val realName: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("heartRate")
    val heart: Double
)
