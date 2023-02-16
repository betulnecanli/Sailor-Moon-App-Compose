package com.betulnecanli.sailormoonapp.presentation.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
        Scaffold(topBar = {
                HomeTopBar(onSearchClicked = {})
        }){}
}