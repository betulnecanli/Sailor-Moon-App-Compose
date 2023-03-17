package com.betulnecanli.sailormoonapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.ui.theme.INFO_ICON_SIZE
import com.betulnecanli.sailormoonapp.ui.theme.SMALL_PADDING
import com.betulnecanli.sailormoonapp.ui.theme.titleColor


@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_ICON_SIZE),
            painter = icon,
            contentDescription = "Info Icon",
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InfoBoxPreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.baseline_heart),
        iconColor = MaterialTheme.colors.primary,
        bigText = "92",
        smallText = "Power",
        textColor = MaterialTheme.colors.titleColor
    )
}