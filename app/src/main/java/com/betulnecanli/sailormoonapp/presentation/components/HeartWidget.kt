package com.betulnecanli.sailormoonapp.presentation.components

import android.hardware.lights.Light
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.ui.theme.Pink200
import com.betulnecanli.sailormoonapp.ui.theme.heartColor

@Composable
fun HeartWidget(
    modifier: Modifier,
    heart: Double,
    scaleFactor: Float = 3f
){
    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember{
        PathParser().parsePathString(pathData = heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }

    FilledHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = scaleFactor)

}


@Composable
fun FilledHeart(
    heartPath : Path,
    heartPathBounds: Rect,
    scaleFactor : Float

){
            Canvas(modifier = Modifier.size(24.dp)){
                val canvasSize = this.size

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
        val canvasSize = this.size

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
        val canvasSize = this.size

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
