package com.betulnecanli.sailormoonapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Pink200 = Color(0xFFE77B9F)
val Pink500 = Color(0xFFE91E63)
val Pink700 = Color(0xFFB90945)
val heartColor = Color(0xFFE7263D)

val heartColor2 = Color(0xFFE7263D)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val LightGray = Color(0xFF6F6E70)
val DarkGray = Color(0xFF2F2F30)
val Colors.welcomeScreenBackgroundColor
    @Composable
    get() = if(isLight) Color.White else Color.Black

val Colors.titleColor
    @Composable
    get() = if(isLight) DarkGray else LightGray

val Colors.statusBarColor
    get() = if (isLight) heartColor else Color.Black

val Colors.descriptionColor
    @Composable
    get() = if(isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)

val Colors.activeIndicatorColor
    @Composable
    get() = if(isLight) Pink200 else Pink700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if(isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    @Composable
    get() = if(isLight) Pink200 else Pink700

val Colors.topAppBarContentColor : Color
    @Composable
    get() = if(isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor : Color
    @Composable
    get() = if (isLight) Pink500 else Color.Black
