package com.betulnecanli.sailormoonapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.betulnecanli.sailormoonapp.presentation.screens.details.DetailsScreen
import com.betulnecanli.sailormoonapp.presentation.screens.home.HomeScreen
import com.betulnecanli.sailormoonapp.presentation.screens.search.SearchScreen
import com.betulnecanli.sailormoonapp.presentation.screens.splash.SplashScreen
import com.betulnecanli.sailormoonapp.presentation.screens.welcome.WelcomeScreen
import com.betulnecanli.sailormoonapp.utils.Constants.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String){
    NavHost(navController = navController,
        startDestination = startDestination){

    /*    composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }*/
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route)
        {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route){
                SearchScreen(navController = navController)
        }

    }

}