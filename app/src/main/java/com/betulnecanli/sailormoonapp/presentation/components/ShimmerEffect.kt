package com.betulnecanli.sailormoonapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.betulnecanli.sailormoonapp.ui.theme.ABOUT_PLACE_HOLDER_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.CHARACTER_ITEM_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.EXTRA_SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.LARGE_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.MEDIUM_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.RATING_PLACE_HOLDER_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.SHIMMER_NAME_PLACEHOLDER_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.ShimmerDarkGray
import com.betulnecanli.sailormoonapp.ui.theme.ShimmerLightGray
import com.betulnecanli.sailormoonapp.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect(){

    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(2){
            AnimatedShimmerItem()
        }
    }
}


@Composable
fun AnimatedShimmerItem(){
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f ,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
        
    ShimmerItem(alpha = alphaAnim)
    

}
@Composable
fun ShimmerItem(alpha : Float){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(CHARACTER_ITEM_HEIGHT),
            color = if(isSystemInDarkTheme())
                Color.Black else ShimmerLightGray,
            shape = RoundedCornerShape(size = LARGE_PADDING)
        ){
            Column(
                modifier = Modifier
                    .padding(all = MEDIUM_PADDING),
                verticalArrangement = Arrangement.Bottom
            ){
                Surface( modifier = Modifier
                    .alpha(alpha)
                    .fillMaxWidth(0.5f)
                    .height(SHIMMER_NAME_PLACEHOLDER_HEIGHT),
                color = if(isSystemInDarkTheme())
                   ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = MEDIUM_PADDING)
                    ){}
                Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
                repeat(2) {
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .fillMaxWidth()
                            .height(ABOUT_PLACE_HOLDER_HEIGHT),
                        color = if (isSystemInDarkTheme())
                            ShimmerDarkGray else ShimmerMediumGray,
                        shape = RoundedCornerShape(size = MEDIUM_PADDING)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
                }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        repeat(5){
                            Surface( modifier = Modifier
                                .alpha(alpha)
                                .size(RATING_PLACE_HOLDER_HEIGHT),
                                color = if(isSystemInDarkTheme())
                                    ShimmerDarkGray else ShimmerMediumGray,
                                shape = RoundedCornerShape(size = MEDIUM_PADDING)
                            ){}
                            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
                        }
                    }
                }
            }
        }


@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ShimmerPreview(){
    AnimatedShimmerItem()
}