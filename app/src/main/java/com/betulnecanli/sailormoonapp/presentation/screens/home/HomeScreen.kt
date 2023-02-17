package com.betulnecanli.sailormoonapp.presentation.screens.home
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.betulnecanli.sailormoonapp.presentation.components.HeartWidget
import com.betulnecanli.sailormoonapp.ui.theme.LARGE_PADDING


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
        homeViewModel: HomeViewModel = hiltViewModel()
){

        val allCharacters = homeViewModel.getAllCharacters

        Scaffold(
                topBar = {
                HomeTopBar(onSearchClicked = {}
                )
        }
        ){
                HeartWidget(
                        modifier = Modifier.padding(all = LARGE_PADDING),
                        heart = 2.1)
        }
}

