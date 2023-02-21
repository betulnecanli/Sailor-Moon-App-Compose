package com.betulnecanli.sailormoonapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.betulnecanli.sailormoonapp.data.remote.SailorApi
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon

class SearchCharactersSource(
    private val sailorApi  :SailorApi,
    private val query : String
) : PagingSource<Int, SailorMoon>() {
    override fun getRefreshKey(state: PagingState<Int, SailorMoon>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SailorMoon> {
        return try {
                val apiResponse = sailorApi.searchCharacters(name = query)
            val characters = apiResponse.sailorMoon
            if(characters.isNotEmpty()){
                LoadResult.Page(
                    data = characters,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }
            else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }catch (e : Exception){
                LoadResult.Error(e)
        }
    }
}