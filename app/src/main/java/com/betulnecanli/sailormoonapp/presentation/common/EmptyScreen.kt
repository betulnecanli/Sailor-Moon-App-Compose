package com.betulnecanli.sailormoonapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error : LoadState.Error? = null,
                characters: LazyPagingItems<SailorMoon>? = null
                ){
        var message  by remember{
            mutableStateOf("Find your favorite character!")
        }
        var icon by remember{
            mutableStateOf(R.drawable.ic_search_document)
        }

    if(error != null){
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

        var startAnimation by remember {
            mutableStateOf(false)
        }

        val alphaAnim by animateFloatAsState(
            targetValue = if(startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
        )

    LaunchedEffect(key1 = true){
        startAnimation = true
    }
    

    EmptyContent(alphaAnim = alphaAnim,
        icon = icon, message = message,
        characters = characters,
        error = error)

}

@Composable
fun EmptyContent(alphaAnim: Float,
                 icon : Int,
                 message : String,
                 error: LoadState.Error? = null,
                 characters : LazyPagingItems<SailorMoon>? = null
                 ){

    var isRefreshing by remember { mutableStateOf(false) }

   SwipeRefresh(
       swipeEnabled = error != null,
       state = rememberSwipeRefreshState(isRefreshing),
       onRefresh = {
           isRefreshing = true
           characters?.refresh()
           isRefreshing = false
       }
   ){
       Column(
           modifier = Modifier.fillMaxSize()
               .verticalScroll(rememberScrollState()),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ){
           Icon(
               modifier = Modifier
                   .size(NETWORK_ERROR_ICON_HEIGHT)
                   .alpha(alpha = alphaAnim),
               painter = painterResource(id = icon) ,
               contentDescription = "Network Error Icon",
               tint= if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
           )
           Text(
               modifier = Modifier.padding(top=SMALL_PADDING),
               text = message,
               color = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.Medium,
               fontSize = MaterialTheme.typography.subtitle1.fontSize
           )
       }
   }

}

fun parseErrorMessage(error: LoadState.Error) : String{
    return when(error.error){
       is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Internet Unavailable."
        }
        else -> {
            "Unknown Error."
        }
    }
}


@Composable
@Preview(showBackground = true)
fun EmptyScreen(){
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}


@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun EmptyDarkScreen(){
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}