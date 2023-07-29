package com.betulnecanli.sailormoonapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.betulnecanli.sailormoonapp.utils.Constants.BASE_URL
import com.betulnecanli.sailormoonapp.utils.PaletteGenerator.convertImageUrlToBitmap
import com.betulnecanli.sailormoonapp.utils.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    navController : NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
){

    val selectedCharacter by detailsViewModel.selectedCharacter.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(navController = navController, selectedCharacter = selectedCharacter, colors = colorPalette)
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        detailsViewModel.uiEvent.collectLatest{event->
            when (event){
                is UiEvent.GenerateColorPalette ->{
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedCharacter?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }

        }
    }



}