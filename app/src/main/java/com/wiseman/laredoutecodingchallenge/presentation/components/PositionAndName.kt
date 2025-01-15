package com.wiseman.laredoutecodingchallenge.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.wiseman.laredoutecodingchallenge.ui.theme.LocalSpacing
import com.wiseman.laredoutecodingchallenge.ui.theme.LocalTextSizes

@Composable
fun PositionAndName(
    modifier: Modifier = Modifier,
    position: Int, name: String
) {
    val spacing = LocalSpacing.current
    val textSize = LocalTextSizes.current
    Column(modifier = modifier) {
        Text(
            text = "$position",
            style = TextStyle(
                color = Color.Gray,
                fontSize = textSize.large,
            )
        )
        Text(
            text = name,
            style = TextStyle(
                color = Color.LightGray,
                fontSize = textSize.body,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
            ),
            modifier = Modifier.padding(top = spacing.spaceExtraSmall)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPositionAndName() {
    PositionAndName(position = 1, name = "BeeWare")
}
