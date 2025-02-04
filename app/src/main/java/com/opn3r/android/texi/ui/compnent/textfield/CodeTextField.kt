package com.opn3r.android.texi.ui.compnent.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false
) {
    val animatedColor by animateColorAsState(
        targetValue = if (value.isEmpty() || readOnly) Color(0xFFB4B4B4) else Color(0xFFFFCC01),
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    val animatedBackground by animateColorAsState(
        targetValue = if (readOnly) Color.Black else Color.White,
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    val show by animateFloatAsState(
        targetValue = if(readOnly) 0.1F else 0F, label = ""
    )

    Box(
        modifier = modifier
            .height(50.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = animatedColor,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .alpha(show)
                .background(
                    color = animatedBackground,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxSize()
        )
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)

            },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(horizontal = 10.dp),
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            ),
            readOnly = readOnly
        )
    }
}


@Composable
@Preview
fun CodeTextFieldPreview() {
    val text = remember { mutableStateOf("1") }
    CodeTextField(
        value = text.value,
        onValueChange = { text.value },
        readOnly = true
    )
}