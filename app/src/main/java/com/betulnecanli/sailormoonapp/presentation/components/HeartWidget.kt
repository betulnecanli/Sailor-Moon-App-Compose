package com.betulnecanli.sailormoonapp.presentation.components


import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.ui.theme.EXTRA_SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.Pink200
import com.betulnecanli.sailormoonapp.ui.theme.heartColor

@Composable
fun HeartWidget(
    modifier: Modifier,
    heart: Double,
    scaleFactor: Float = 3f,
    spaceBetween : Dp = EXTRA_SMALL_PADDING
){

    val result = CalculateHearts(rating = heart)

    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember{
        PathParser().parsePathString(pathData = heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }

    Row(modifier  = modifier,
    horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ){
        result["filledHeart"]?.let {
            repeat(it){
                FilledHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["halfFilledHearts"]?.let {
            repeat(it){
                HalfFilledHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["emptyHearts"]?.let {
            repeat(it){
                EmptyHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
    }




}


@Composable
fun FilledHeart(
    heartPath : Path,
    heartPathBounds: Rect,
    scaleFactor : Float
){
            Canvas(modifier = Modifier.size(24.dp)){
                val canvasSize = size

                scale(scale = scaleFactor){
                    val pathWidth  = heartPathBounds.width
                    val pathHeight = heartPathBounds.height
                    val left = (canvasSize.width/2) - (pathWidth/2)
                    val top = (canvasSize.height/2) - (pathHeight/2)

                    translate(left = left, top = top) {
                        drawPath(
                            path = heartPath,
                            color = heartColor
                        )
                    }
                }
            }
}

@Composable
fun HalfFilledHeart(
    heartPath : Path,
    heartPathBounds: Rect,
    scaleFactor : Float
){
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = size

        scale(scale = scaleFactor){
            val pathWidth  = heartPathBounds.width
            val pathHeight = heartPathBounds.height
            val left = (canvasSize.width/2) - (pathWidth/2)
            val top = (canvasSize.height/2) - (pathHeight/2)

            translate(left = left, top = top) {
                drawPath(
                    path = heartPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                clipPath(path = heartPath){
                    drawRect(
                        color = heartColor,
                        size = Size(
                            width = heartPathBounds.maxDimension / 2f,
                            height = heartPathBounds.maxDimension * scaleFactor

                        )
                    )
                }
            }
        }
    }
}


@Composable
fun EmptyHeart(
    heartPath : Path,
    heartPathBounds: Rect,
    scaleFactor : Float
){
    Canvas(modifier = Modifier.size(24.dp)){
        val canvasSize = size

        scale(scale = scaleFactor){
            val pathWidth  = heartPathBounds.width
            val pathHeight = heartPathBounds.height
            val left = (canvasSize.width/2) - (pathWidth/2)
            val top = (canvasSize.height/2) - (pathHeight/2)

            translate(left = left, top = top) {
                drawPath(
                    path = heartPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }


    }
}


@Composable
fun CalculateHearts(rating : Double) : Map<String, Int>{
    val maxHearts by remember {
        mutableStateOf(5)
    }
    var filledHearts by remember {
        mutableStateOf(0)
    }
    var halfFilledHearts by remember {
        mutableStateOf(0)
    }
    var emptyHearts by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = rating ){
        val(firstNumber, secondNumber)  = rating.toString()
            .split(".")
            .map{
                it.toInt()
            }
        if(firstNumber in 1..5  && secondNumber in 0..9){
            filledHearts = firstNumber
            if(secondNumber in 1..5){
                halfFilledHearts++
            }
            if(secondNumber in 6..9 ){
                filledHearts++
            }
            if(firstNumber == 5 && secondNumber > 0){
                emptyHearts = 5
                filledHearts = 0
                halfFilledHearts = 0
            }
        }
        else{
            Log.d("HeartWidget","Invalid rating number.")
        }

    }
    emptyHearts = maxHearts - (filledHearts + halfFilledHearts)
    return mapOf(
        "filledHeart" to filledHearts,
        "halfFilledHearts" to halfFilledHearts,
        "emptyHearts" to emptyHearts
    )
}

@Composable
@Preview(showBackground = true)
fun showP(){
    val result = CalculateHearts(rating = 5.0)

    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember{
        PathParser().parsePathString(pathData = heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }

    Row(modifier  = Modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        result["filledHeart"]?.let {
            repeat(it){
                FilledHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = 3f
                )
                Log.d("heart", it.toString())
            }
        }
        result["halfFilledHearts"]?.let {
            repeat(it){
                HalfFilledHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = 3f
                )
            }
        }
        result["emptyHearts"]?.let {
            repeat(it){
                EmptyHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = 3f
                )
            }
        }
    }

}
@Composable
@Preview(showBackground = true)
fun FilledHeartPreview(){
    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember{
        PathParser().parsePathString(pathData = heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }
    FilledHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = 3f)

}
@Composable
@Preview(showBackground = true)
fun HalfFilledHeartPreview(){
    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember{
        PathParser().parsePathString(pathData = heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }
   HalfFilledHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = 3f)
}

@Composable
@Preview(showBackground = true)
fun EmptyFilledHeartPreview(){
    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember{
        PathParser().parsePathString(pathData = heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }
    EmptyHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = 3f)
}
