package com.opn3r.android.texi.ui.compnent.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import notoSanskr
import com.opn3r.android.texi.R

@Composable
fun DeleteTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onClick: () -> Unit,
    readOnly: Boolean,
    error: Boolean = false
) {
    val animatedColor by animateColorAsState(
        targetValue = if (readOnly) Color.Gray else Color.Black,
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )
    Box {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF1F1F5),
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomEnd = if (error) 0.dp else 8.dp,
                        bottomStart = if (error) 0.dp else 8.dp
                    )
                )
                .padding(vertical = 15.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = modifier
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    fontSize = 15.sp,
                                    text = hint,
                                    style = TextStyle(
                                        fontFamily = notoSanskr,
                                        fontWeight = FontWeight.Medium,
                                        color = animatedColor,
                                    ),
                                    color = Color.Gray,
                                )
                            }
                            innerTextField()
                        }
                    },
                    readOnly = readOnly,
                    textStyle = TextStyle(
                        color = animatedColor
                    )
                )
                if (readOnly) {
                    Image(
                        painter = painterResource(R.drawable.checkhi),
                        contentDescription = null,
                        modifier = modifier
                            .size(20.dp)
                    )
                } else {
                    Box(
                        modifier = modifier
                            .clickable {
                                onClick()
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ellipse_6),
                            contentDescription = null,
                            modifier = modifier
                                .size(20.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.x),
                            contentDescription = null,
                            modifier = modifier
                                .size(10.dp)
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
        }
        if (error) {
            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = colorResource(R.color.error_red))
                    .align(alignment = Alignment.BottomCenter)
            )
        }
    }
}

@Composable
@Preview
fun DeleteTextFieldPreview() {
    val test = remember { mutableStateOf("") }
    DeleteTextField(
        value = test.value,
        onValueChange = { test.value = it },
        hint = "굿굿",
        onClick = {},
        readOnly = false
    )
}