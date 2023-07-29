package com.betulnecanli.sailormoonapp.presentation.screens.home
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.navigation.Screen
import com.betulnecanli.sailormoonapp.presentation.common.ListContent
import com.betulnecanli.sailormoonapp.presentation.components.HeartWidget
import com.betulnecanli.sailormoonapp.presentation.components.ShimmerEffect
import com.betulnecanli.sailormoonapp.ui.theme.LARGE_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.titleColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
        navController : NavHostController,
        homeViewModel: HomeViewModel = hiltViewModel()
){


        val allCharacters = homeViewModel.getAllCharacters.collectAsLazyPagingItems()
        val systemUiController = rememberSystemUiController()
        val systemBarColor = MaterialTheme.colors.titleColor

        SideEffect {
                systemUiController.setStatusBarColor(
                        color = systemBarColor
                )
        }

        Scaffold(
                topBar = {
                        HomeTopBar(
                                onSearchClicked = {
                                        navController.navigate(Screen.Search.route)
                                }
                        )
                },
                content = {
                        ListContent(
                                characters = allCharacters,
                                navController = navController
                        )
                }
        )
}


