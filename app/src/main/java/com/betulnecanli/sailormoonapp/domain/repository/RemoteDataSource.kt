package com.betulnecanli.sailormoonapp.domain.repository

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource{

        fun getAllCharacters() : Flow<PagingData<SailorMoon>>
        fun searchCharacters(query : String) : Flow<PagingData<SailorMoon>>
}