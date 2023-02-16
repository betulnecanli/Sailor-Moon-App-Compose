package com.betulnecanli.sailormoonapp.presentation.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
        homeViewModel: HomeViewModel = hiltViewModel()
){

        val allCharacters = homeViewModel.getAllCharacters

        Scaffold(topBar = {
                HomeTopBar(onSearchClicked = {})
        }){}
}