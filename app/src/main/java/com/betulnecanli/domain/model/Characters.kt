package com.betulnecanli.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulnecanli.sailormoonapp.utils.Constants.SAILOR_DATABASE_TABLE

@Entity(tableName = SAILOR_DATABASE_TABLE)
data class Characters(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val realName: String,
    val birthday: String,
    val age: Int?,
    val species: String
)
