package com.betulnecanli.sailormoonapp.presentation.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.navigation.Screen
import com.betulnecanli.sailormoonapp.presentation.components.HeartWidget
import com.betulnecanli.sailormoonapp.ui.theme.CHARACTER_ITEM_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.LARGE_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.MEDIUM_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.topAppBarContentColor
import com.betulnecanli.sailormoonapp.utils.Constants.BASE_URL

@Composable
fun ListContent(
    characters : LazyPagingItems<SailorMoon>,
    navController : NavHostController
){

}



@Composable
fun CharacterItem(
    character : SailorMoon,
    navController: NavHostController
){

    val painter  = rememberImagePainter(data = "$BASE_URL${character.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .height(CHARACTER_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passCharacterId(chId = character.id))
        },
    contentAlignment = Alignment.BottomStart
        ){
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop)
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING ,
                bottomEnd = LARGE_PADDING
            )
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = MEDIUM_PADDING)
        ){
            Text(text = character.name,
            color = MaterialTheme.colors.topAppBarContentColor,
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
            Text(text = character.species,
                color = Color.White.copy(alpha = ContentAlpha.medium),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis)
            Row(modifier = Modifier.padding(top = SMALL_PADDING), verticalAlignment = Alignment.CenterVertically){
                    HeartWidget(modifier = Modifier.padding(end = SMALL_PADDING), heart = character.heart )
                    Text(text = "(${character.heart})", textAlign = TextAlign.Center)
            }
        }

        }
    }
}

@Composable
@Preview
fun CharacterPreview(){
    CharacterItem(character = SailorMoon(
        id = 1,
        name = "Sailor Moon",
        age = 17,
        birthday ="April 5",
        heart = 2.0,
        image ="" ,
        realName = "",
        species = "Human"

    ), navController = rememberNavController())
}