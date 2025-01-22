package com.opn3r.android.texi.ui.compnent.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opn3r.android.texi.R

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    click: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                shape = CircleShape,
                color = if (click) colorResource(R.color.white_yellow) else Color.Transparent,
            )
            .clickable { onClick() }
            .size(25.dp)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = if (click) colorResource(R.color.white_yellow) else colorResource(R.color.m_line)
            )
            .padding(5.dp)
            .padding(top = 2.dp)
    ) {
        if (click) {
            Image(
                painter = painterResource(R.drawable.backbtnwhite),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun CircleButtonPreView() {
    CircleButton(
        onClick = {}
    )
}