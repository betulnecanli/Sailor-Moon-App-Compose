package com.betulnecanli.sailormoonapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases : UseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedCharacters = MutableStateFlow<PagingData<SailorMoon>>(PagingData.empty())
    val searchCharacters = _searchedCharacters

    fun updateSearchQuery(query: String){
        _searchQuery.value = query
    }

    fun searchCharacters(query: String){
        viewModelScope.launch(Dispatchers.IO){
            useCases.searchCharactersUseCase(query = query).cachedIn(viewModelScope).collect{
                _searchedCharacters.value = it
            }
        }
    }


}