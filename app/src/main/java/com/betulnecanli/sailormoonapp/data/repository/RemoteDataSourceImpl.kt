package com.betulnecanli.sailormoonapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.betulnecanli.sailormoonapp.data.local.SailorDatabase
import com.betulnecanli.sailormoonapp.data.paging_source.SailorRemoteMediator
import com.betulnecanli.sailormoonapp.data.remote.SailorApi
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val api : SailorApi,
    private val db : SailorDatabase
) : RemoteDataSource {

    private val SailorDao = db.sailorDao()
    private val RemoteKeysDao = db.remoteKeyDao()

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

    override fun searchCharacters(): Flow<PagingData<SailorMoon>> {
        TODO("Not yet implemented")
    }
}