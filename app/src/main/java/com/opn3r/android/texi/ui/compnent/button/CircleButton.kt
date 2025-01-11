package com.opn3r.android.texi.ui.compnent.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                shape = CircleShape,
                color = Color.Transparent,
            )
            .border(width = 2.dp, shape = CircleShape, color = Color.White)
    )
}

@Composable
@Preview
fun CircleButtonPreView() {
    CircleButton()
}