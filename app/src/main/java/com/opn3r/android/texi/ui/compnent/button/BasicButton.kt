package com.opn3r.android.texi.ui.compnent.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import notoSanskr

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(bottom = 10.dp)
                .background(color = Color(0xFFFFCC01), shape = RoundedCornerShape(size = 12.dp))
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = notoSanskr,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(18.dp))
                .blur(radius = 15.dp)
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 20.dp)
                .background(color = Color(0x4DFECE23))
        )
    }
}


@Composable
@Preview
fun BasicButtonPreview() {
    BasicButton(
        text = "안뇽",
        onClick = {

        }
    )
}