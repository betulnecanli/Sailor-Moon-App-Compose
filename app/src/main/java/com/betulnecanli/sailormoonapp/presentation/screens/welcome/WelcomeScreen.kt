package com.betulnecanli.sailormoonapp.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.betulnecanli.sailormoonapp.domain.model.OnBoardingPage
import com.betulnecanli.sailormoonapp.ui.theme.welcomeScreenBackgroundColor
import com.betulnecanli.sailormoonapp.utils.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.betulnecanli.sailormoonapp.navigation.Screen
import com.betulnecanli.sailormoonapp.ui.theme.EXTRA_LARGE_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.PAGING_INDICATOR_SPACING
import com.betulnecanli.sailormoonapp.ui.theme.PAGING_INDICATOR_WIDTH
import com.betulnecanli.sailormoonapp.ui.theme.SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.activeIndicatorColor
import com.betulnecanli.sailormoonapp.ui.theme.buttonBackgroundColor
import com.betulnecanli.sailormoonapp.ui.theme.descriptionColor
import com.betulnecanli.sailormoonapp.ui.theme.inactiveIndicatorColor
import com.betulnecanli.sailormoonapp.ui.theme.titleColor
import com.betulnecanli.sailormoonapp.utils.Constants.LAST_ON_BOARDING_PAGE
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(navController : NavHostController,
                    welcomeViewModel : WelcomeViewModel = hiltViewModel()
                  ){
        val pages = listOf(
            OnBoardingPage.First,
            OnBoardingPage.Second,
            OnBoardingPage.Third
        )

    val pagerState = rememberPagerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)) {
        HorizontalPager(modifier = Modifier.weight(10f),
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
        verticalAlignment = Alignment.Top
            ) {position->
            PagerScreen(onBoardingPage = pages[position] )
        }

            HorizontalPagerIndicator(modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
                pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING)

        FinishButton(modifier = Modifier.weight(1f),
            pagerState = pagerState) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }

    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(modifier : Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
){
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ){
        AnimatedVisibility(modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE) {
            Button(onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                contentColor = Color.White
            )
                ) {
                Text(text = "Finish")
            }
        }

    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(modifier = Modifier
        .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top) {
        Image( modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "On Boarding Image"
        )
        Text(modifier = Modifier
            .fillMaxWidth(),
            text = onBoardingPage.title,
        color = MaterialTheme.colors.titleColor,
        fontSize = MaterialTheme.typography.h4.fontSize,
        fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(vertical = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}