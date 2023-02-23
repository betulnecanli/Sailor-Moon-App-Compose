package com.betulnecanli.sailormoonapp.data.repository

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.repository.DatastoreOperations
import com.betulnecanli.sailormoonapp.domain.repository.LocalDataSource
import com.betulnecanli.sailormoonapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local : LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DatastoreOperations
) {

    fun getAllCharacters(): Flow<PagingData<SailorMoon>>{
        return remote.getAllCharacters()
    }

    fun searchCharacters(query : String): Flow<PagingData<SailorMoon>>{
        return  remote.searchCharacters(query)
    }

    suspend fun getSelectedCharacter(charID : Int): SailorMoon{
        return local.getSelectedCharacter(charID)
    }

    suspend fun saveOnBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}