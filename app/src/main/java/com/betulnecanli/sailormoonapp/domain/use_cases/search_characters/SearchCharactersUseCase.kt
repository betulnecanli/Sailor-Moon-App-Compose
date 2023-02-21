package com.betulnecanli.sailormoonapp.domain.use_cases.search_characters

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.repository.Repository
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import kotlinx.coroutines.flow.Flow

class SearchCharactersUseCase(
    private val repository : Repository
) {

    operator fun invoke(query: String) : Flow<PagingData<SailorMoon>>{
        return repository.searchCharacters(query)
    }
}