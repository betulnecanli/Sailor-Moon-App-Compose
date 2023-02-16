package com.betulnecanli.sailormoonapp.domain.use_cases.get_all_characters

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.repository.Repository
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository : Repository
) {
    operator fun invoke(): Flow<PagingData<SailorMoon>>{
        return repository.getAllCharacters()
    }
}