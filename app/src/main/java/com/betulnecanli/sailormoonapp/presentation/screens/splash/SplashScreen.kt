package com.betulnecanli.sailormoonapp.presentation.screens.splash
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.navigation.Screen
import com.betulnecanli.sailormoonapp.ui.theme.Pink500
import com.betulnecanli.sailormoonapp.ui.theme.Pink700

@Composable
fun SplashScreen(navController: NavHostController,
                    splashViewModel: SplashViewModel = hiltViewModel())
{
        val lifecycleOwner = LocalLifecycleOwner.current
        val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
        LaunchedEffect(lifecycleOwner) {
        navController.popBackStack()
        if (onBoardingCompleted){
            navController.navigate(Screen.Home.route)

        }
        else{
            navController.navigate(Screen.Welcome.route)

        }
    }




        Splash()
}

@Composable
fun Splash(){
    if(isSystemInDarkTheme()){

        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(Pink700, Pink500)))
            .fillMaxSize(), contentAlignment = Alignment.Center
        ){
            Image(modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop,painter = painterResource(id = R.drawable.sa_dark),
                contentDescription = "App Logo"
            )
        }
    }
    else{

        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(Pink700, Pink500)))
            .fillMaxSize(), contentAlignment = Alignment.Center
        ){
            Image(modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop,painter = painterResource(id = R.drawable.sa),
                contentDescription = "App Logo"
            )
        }
    }

}

@Composable
@Preview
fun SplashScreenPreview(){
    Splash()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview(){
    Splash()
}