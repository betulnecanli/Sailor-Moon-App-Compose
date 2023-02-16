package com.betulnecanli.sailormoonapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.betulnecanli.sailormoonapp.data.local.SailorDatabase
import com.betulnecanli.sailormoonapp.data.remote.SailorApi
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.model.SailorRemoteKey
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SailorRemoteMediator @Inject constructor(
    private val sailorApi: SailorApi,
    private val sailorDB : SailorDatabase
): RemoteMediator<Int, SailorMoon>() {

    private val sailorDao = sailorDB.sailorDao()
    private val sailorRemoteKeyDao = sailorDB.remoteKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SailorMoon>
    ): MediatorResult {
        return try{
            val page = when(loadType){
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyFirstItem(state)
                    val prevPage = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
            }

            val response = sailorApi.getCharacters(page = page)
            if (response.sailorMoon.isNotEmpty()) {

                sailorDB.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        sailorDao.deleteAllCharacters()
                        sailorRemoteKeyDao.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPAge = response.nextPage
                    val keys = response.sailorMoon.map { characters ->
                        SailorRemoteKey(
                            id = characters.id,
                            prevKey = prevPage,
                            nextKey = nextPAge
                        )
                    }

                    sailorRemoteKeyDao.addAllRemoteKeys(keys)
                    sailorDao.addCharacters(response.sailorMoon)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch(e : Exception) {


            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyFirstItem(state: PagingState<Int, SailorMoon>): SailorRemoteKey? {
        return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let {
                sailorRemoteKeyDao.getRemoteKey(it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, SailorMoon>): SailorRemoteKey? {
        return state.pages.lastOrNull(){it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let {
                sailorRemoteKeyDao.getRemoteKey(it.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, SailorMoon>): SailorRemoteKey? {
        return state.anchorPosition?.let {position ->
            state.closestItemToPosition(position)?.id?.let {id->
                sailorRemoteKeyDao.getRemoteKey(id=id )

            }
        }
    }
}