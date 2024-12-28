package com.opn3r.android.texi.ui.compnent.textfield

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import notoSanskr
import com.opn3r.android.texi.R

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onClick: () -> Unit,
    sec: Int,
    readOnly: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF1F1F5), shape = RoundedCornerShape(8.dp))
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
                                    color = Color(0x99999999),
                                ),
                                color = Color.Gray,
                            )
                        }
                        innerTextField()
                    }
                },
                readOnly = readOnly
            )
            if (readOnly) {
                Image(
                    painter = painterResource(R.drawable.checkhi),
                    contentDescription = null,
                    modifier = modifier
                        .size(20.dp)
                )
            } else {
                Text(
                    text = if (sec != 300) sec.toString() else "인증",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 40.sp,
                        fontFamily = notoSanskr,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFCC01),
                        letterSpacing = 0.25.sp,
                    ),
                    modifier = modifier
                        .clickable {
                            onClick()
                        }
                )
            }
        }
        Box(
            modifier = modifier
                .animateContentSize()
                .fillMaxWidth(if (readOnly) 1F else 0F)
                .background(color = Color.Black)
        )
    }
}

@Composable
@Preview
fun AuthTextPreview() {
    val test = remember { mutableStateOf("") }
    AuthTextField(
        value = test.value,
        onValueChange = { test.value = it },
        hint = "굿굿",
        onClick = {},
        sec = 300,
        readOnly = true
    )
}