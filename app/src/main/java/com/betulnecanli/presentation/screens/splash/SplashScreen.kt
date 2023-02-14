package com.betulnecanli.presentation.screens.splash
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.ui.theme.Pink500
import com.betulnecanli.sailormoonapp.ui.theme.Pink700

@Composable
fun SplashScreen(navController: NavHostController){

}

@Composable
fun Splash(){
    Box(modifier = Modifier
        .background(Brush.verticalGradient(listOf(Pink700, Pink500)))
        .fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.sa),
            contentDescription = "App Logo"
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    Splash()
}