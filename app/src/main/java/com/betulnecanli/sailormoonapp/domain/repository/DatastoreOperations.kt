package com.betulnecanli.sailormoonapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreOperations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}