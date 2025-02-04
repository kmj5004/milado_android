package com.opn3r.android.texi.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.opn3r.android.texi.R
import com.opn3r.android.texi.feature.navigation.NavGroup
import kotlinx.coroutines.delay
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import com.opn3r.android.texi.feature.font.notSanskrit

@Composable
fun SplashScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var animation by remember { mutableStateOf(false) }
    var background by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100)
        animation = true
        delay(2000)
        animation = false
        background = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                R.drawable.ta
            ),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
        Column {
            AnimatedVisibility(
                visible = animation,
                enter = slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing
                    ),
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                Text(
                    text = "같이 택시 탈 \n사람을 찾는다면\n완벽한 선택",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = notSanskrit,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 0.25.sp,
                    ),
                    modifier = modifier
                        .padding(top = 40.dp, start = 20.dp)
                )
            }
            AnimatedVisibility(
                visible = animation,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight},
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = 400,
                        easing = LinearOutSlowInEasing
                    ),
                )
            ) {
                Text(
                    text = "MILADO",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = notSanskrit,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        letterSpacing = 0.25.sp,
                    ),
                    modifier = modifier
                        .padding(start = 20.dp)
                )
            }
        }
        AnimatedVisibility(
            visible = background,
            enter = slideInVertically(
                initialOffsetY = { fullFIllHeight -> fullFIllHeight},
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            )
        ) {
            StartScreen(
                navController = navController
            )
        }
    }
}

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var pass by remember { mutableStateOf(false) }
    val animatedColor by animateColorAsState(
        targetValue = if (pass) Color.White else colorResource(R.color.yellow), label = "",
        animationSpec = tween(
            durationMillis = 1500,        // 지속시간
            easing = LinearOutSlowInEasing  // 가속도 곡선
        )
    )
    LaunchedEffect(pass) {
        if (pass) {
            delay(300)
            navController.navigate(NavGroup.AUTH)
        }
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.7f }
                .background(color = Color.Black)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(alignment = Alignment.BottomCenter)
        ) {
            Text(
                text = "같이 택시 탈 \n사람을 찾는다면\n완벽한 선택",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = notSanskrit,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.25.sp,
                ),
                modifier = modifier
            )
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "사람들을 모아보세요",
                    color = Color.White,
                    fontFamily = notSanskrit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
                Box(
                    modifier = modifier
                        .background(
                            shape = CircleShape,
                            color = colorResource(R.color.yellow)
                        )
                        .size(40.dp)
                        .clickable {
                            pass = !pass
                        }
                ) {
                    Image(
                        painter = painterResource(
                            R.drawable.arrow
                        ),
                        contentDescription = null,
                        modifier = modifier
                            .size(25.dp)
                            .align(alignment = Alignment.Center)
                    )
                }
            }
            Spacer(Modifier.height(50.dp))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(5.dp)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .graphicsLayer { alpha = 0.3f }
                        .background(color = Color.White, shape = CircleShape)
                )
                Box(
                    modifier = modifier
                        .animateContentSize()
                        .fillMaxWidth(if (pass) 1.0F else 0.5F)
                        .fillMaxHeight()
                        .background(color = animatedColor, shape = CircleShape)
                )
            }
            Spacer(Modifier.height(40.dp))
        }
    }
}

//@Composable
//@Preview
//fun StartPreview() {
//    StartScreen()
//}


@Composable
@Preview
fun SplashPreview() {
    SplashScreen(
        navController = NavHostController(context = LocalContext.current)
    )
}
