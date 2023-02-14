package com.betulnecanli.sailormoonapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.betulnecanli.sailormoonapp.navigation.SetupNavGraph
import com.betulnecanli.sailormoonapp.ui.theme.SailorMoonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SailorMoonAppTheme {
              Scaffold() {
                  navController = rememberNavController()
                  SetupNavGraph(navController = navController)
              }
            }


            }
        }
    }


