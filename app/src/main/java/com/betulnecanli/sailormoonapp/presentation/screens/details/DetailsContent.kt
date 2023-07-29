package com.betulnecanli.sailormoonapp.presentation.screens.details


import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import android.graphics.Color.parseColor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon
import com.betulnecanli.sailormoonapp.presentation.components.InfoBox
import com.betulnecanli.sailormoonapp.presentation.components.OrderedList
import com.betulnecanli.sailormoonapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.betulnecanli.sailormoonapp.ui.theme.EXTRA_LARGE_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.INFO_ICON_SIZE
import com.betulnecanli.sailormoonapp.ui.theme.LARGE_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.MEDIUM_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.MIN_SHEET_HEIGHT
import com.betulnecanli.sailormoonapp.ui.theme.SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.titleColor
import com.betulnecanli.sailormoonapp.utils.Constants.ABOUT_MAX_TEXT_LINES
import com.betulnecanli.sailormoonapp.utils.Constants.BASE_URL
import com.betulnecanli.sailormoonapp.utils.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedCharacter: SailorMoon?,
    colors: Map<String, String>
){

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedCharacter) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Collapsed)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedCharacter?.let {
                BottomSheetContent(
                    selectedCharacter = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedCharacter?.let { hero ->
                BackgroundContent(
                    characterImage = hero.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedCharacter: SailorMoon,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.baseline_halfmoon),
                contentDescription = "App Logo",
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedCharacter.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = selectedCharacter.species,
                smallText = "Species",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedCharacter.age.toString(),
                smallText = "Age",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedCharacter.birthday,
                smallText = "Birthday",
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedCharacter.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_MAX_TEXT_LINES
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = "Family",
                items =  listOf("Minato", "Kushina"),
                textColor = contentColor
            )
            OrderedList(
                title = "Family",
                items =  listOf("Minato", "Kushina"),
                textColor = contentColor
            )
            OrderedList(
                title = "Family",
                items = listOf("Minato", "Kushina"),
                textColor = contentColor
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    characterImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${characterImage}"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.5f)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.ic_placeholder)
                .build(),
            contentDescription = "Character Image",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
    }
}



@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedCharacter = SailorMoon(
            id = 1,
            name = "Sailor",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            heartRate = 4.5,
            species = "",
            realName = "",
            age = 10,
            birthday = ""
        )
    )
}
