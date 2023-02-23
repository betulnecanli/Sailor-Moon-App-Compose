package com.betulnecanli.sailormoonapp.domain.repository

import com.betulnecanli.sailormoonapp.domain.model.SailorMoon

interface LocalDataSource {
    suspend fun getSelectedCharacter(charID: Int) : SailorMoon
}