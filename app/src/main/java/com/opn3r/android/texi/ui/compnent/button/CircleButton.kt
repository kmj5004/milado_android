package com.opn3r.android.texi.ui.compnent.button

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

    val size by animateFloatAsState(
        targetValue = if (click) 0f else 1f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    Box(
        modifier = modifier
            .background(
                shape = CircleShape,
                color = if (click) colorResource(R.color.white_yellow) else Color.Transparent,
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
            .size(20.dp)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = if (click) colorResource(R.color.white_yellow) else colorResource(R.color.m_line)
            )
            .padding(5.dp)
            .padding(top = 2.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.backbtnwhite),
            contentDescription = null
        )
        Box(
            modifier = modifier
                .align(alignment = Alignment.Center)
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .fillMaxSize(size)
        )
    }
}

@Composable
@Preview
fun CircleButtonPreView() {
    CircleButton(
        onClick = {}
    )
}