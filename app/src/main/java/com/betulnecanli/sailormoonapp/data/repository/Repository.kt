package com.betulnecanli.sailormoonapp.data.repository

import com.betulnecanli.sailormoonapp.domain.repository.DatastoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DatastoreOperations
) {
    suspend fun saveOnBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}