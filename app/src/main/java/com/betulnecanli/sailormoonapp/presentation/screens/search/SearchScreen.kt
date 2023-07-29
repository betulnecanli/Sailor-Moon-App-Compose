package com.betulnecanli.sailormoonapp.presentation.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.betulnecanli.sailormoonapp.presentation.common.ListContent
import com.betulnecanli.sailormoonapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
){
    val searchQuery by searchViewModel.searchQuery
    val characters = searchViewModel.searchCharacters.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor


    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                    Log.d("mettun1", it)
                },
                onSearchClicked = {
                    searchViewModel.searchCharacters(query = it)
                    Log.d("mettun2", it)
                },
                onCloseClicked = {
                    navController.popBackStack()

                }
            )
        },
        content = {
            ListContent(characters = characters , navController = navController)
        }
    )
}