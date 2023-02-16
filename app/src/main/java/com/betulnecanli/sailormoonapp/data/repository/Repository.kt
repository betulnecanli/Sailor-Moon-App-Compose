package com.betulnecanli.sailormoonapp.data.repository

import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.repository.DatastoreOperations
import com.betulnecanli.sailormoonapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DatastoreOperations
) {

    fun getAllCharacters(): Flow<PagingData<SailorMoon>>{
        return remote.getAllCharacters()
    }

    suspend fun saveOnBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}