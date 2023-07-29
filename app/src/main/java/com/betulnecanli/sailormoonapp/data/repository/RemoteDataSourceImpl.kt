package com.betulnecanli.sailormoonapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.local.SailorDatabase
import com.betulnecanli.sailormoonapp.data.paging_source.SailorRemoteMediator
import com.betulnecanli.sailormoonapp.data.paging_source.SearchCharactersSource
import com.betulnecanli.sailormoonapp.data.remote.SailorApi
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val api : SailorApi,
    private val db : SailorDatabase
) : RemoteDataSource {

    private val SailorDao = db.sailorDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCharacters(): Flow<PagingData<SailorMoon>> {
        val pagingSourceFactory = {SailorDao.gelAllCharacters()}
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = SailorRemoteMediator(
                api, db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


    override fun searchCharacters(query : String): Flow<PagingData<SailorMoon>> {
       return Pager(
           config = PagingConfig(pageSize = 3),
           pagingSourceFactory = {
               SearchCharactersSource(api,query)
           }
       ).flow
    }
}