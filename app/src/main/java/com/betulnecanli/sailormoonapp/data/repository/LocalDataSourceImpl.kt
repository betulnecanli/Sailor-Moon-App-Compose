package com.betulnecanli.sailormoonapp.data.repository

import com.betulnecanli.sailormoonapp.data.local.SailorDatabase
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(sailorDB : SailorDatabase) : LocalDataSource {

    private val sailorDao  = sailorDB.sailorDao()

    override suspend fun getSelectedCharacter(charID: Int): SailorMoon {
       return sailorDao.getSelectedCharacter(charID)
    }
}