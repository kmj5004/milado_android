package com.opn3r.android.texi.feature.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.opn3r.android.texi.R
import com.opn3r.android.texi.ui.compnent.button.BasicButton
import com.opn3r.android.texi.ui.compnent.textfield.AuthTextField
import com.opn3r.android.texi.ui.compnent.textfield.CodeTextField
import com.opn3r.android.texi.ui.compnent.textfield.DeleteTextField
import kotlinx.coroutines.delay
import notoSanskr
import kotlin.time.Duration.Companion.seconds

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SignUpViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var code by remember { mutableStateOf(false) }
    val focusManager: FocusManager = LocalFocusManager.current
    var readOnly by remember { mutableIntStateOf(0) }
    var name by remember { mutableStateOf(false) }
    var dep by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.clicked) {
        if (uiState.clicked) {
            while (uiState.sec >= 0) {
                viewModel.delSec(uiState.sec)
                delay(1.seconds)
            }
            viewModel.updateClicked(false)
        }
    }

    LaunchedEffect(readOnly) {
        if (readOnly == 2) {
            dep = true
        }
    }

    fun updateTel(tel: String) {
        viewModel.updateTel(tel)
        viewModel.updateClicked(false)
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp)
    ) {
        Column {
            Row {
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = modifier
                        .size(30.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "회원가입",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 90.sp,
                        fontFamily = notoSanskr,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF191919),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
            Spacer(Modifier.height(80.dp))
            Column {
                Text(
                    text = "함께타는\n택시의\n첫걸음",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = notoSanskr,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                )
                Spacer(Modifier.height(30.dp))
                Column {
                    AnimatedVisibility(
                        visible = dep,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight },
                            animationSpec = tween(
                                durationMillis = 500,        // 지속시간
                                delayMillis = 100,          // 시작 전 대기시간
                                easing = LinearOutSlowInEasing  // 가속도 곡선
                            )
                        ),
                    ) {
                        DeleteTextField(
                            value = uiState.dep,
                            onValueChange = { newValue -> viewModel.updateDep(newValue) },
                            hint = "학번을 입력해 주세요",
                            onClick = {
                                viewModel.updateDep("")
                            },
                            readOnly = readOnly >= 2,
                            modifier = modifier.zIndex(1F)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    AnimatedVisibility(
                        visible = name,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight },
                            animationSpec = tween(
                                durationMillis = 500,        // 지속시간
                                delayMillis = 100,          // 시작 전 대기시간
                                easing = LinearOutSlowInEasing  // 가속도 곡선
                            )
                        ),
                    ) {
                        DeleteTextField(
                            value = uiState.name,
                            onValueChange = { newValue -> viewModel.updateName(newValue) },
                            hint = "이름를 입력해 주세요",
                            onClick = {
                                viewModel.updateName("")
                            },
                            readOnly = readOnly >= 2,
                            modifier = modifier.zIndex(1F)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    AuthTextField(
                        value = uiState.tel,
                        onValueChange = { newValue -> updateTel(newValue) },
                        hint = "전화번호를 입력해 주세요",
                        sec = uiState.sec,
                        onClick = {
                            viewModel.updateClicked(true)
                            code = true
                        },
                        readOnly = readOnly >= 1,
                        modifier = modifier.zIndex(1F)
                    )
                    Spacer(Modifier.height(15.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AnimatedVisibility(
                            visible = code,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 30,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            modifier = Modifier.weight(1F)
                        ) {
                            CodeTextField(
                                value = uiState.code,
                                onValueChange = { newValue ->
                                    viewModel.updateCode(newValue)
                                    if (newValue.length > uiState.code.length) {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    } else {
                                        focusManager.clearFocus()
                                    }
                                },
                                readOnly = readOnly >= 1,
                            )
                        }
                        AnimatedVisibility(
                            visible = code,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 40,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            modifier = Modifier.weight(1F)
                        ) {
                            CodeTextField(
                                value = uiState.code2,
                                onValueChange = { newValue ->
                                    viewModel.updateCode2(newValue)
                                    if (newValue.length > uiState.code2.length) {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    } else {
                                        focusManager.moveFocus(FocusDirection.Left)
                                    }
                                },
                                readOnly = readOnly >= 1,
                            )
                        }
                        AnimatedVisibility(
                            visible = code,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 50,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            modifier = Modifier.weight(1F)
                        ) {
                            CodeTextField(
                                value = uiState.code3,
                                onValueChange = { newValue ->
                                    viewModel.updateCode3(newValue)
                                    if (newValue.length > uiState.code3.length) {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    } else {
                                        focusManager.moveFocus(FocusDirection.Left)
                                    }
                                },
                                readOnly = readOnly >= 1,
                            )
                        }
                        AnimatedVisibility(
                            visible = code,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 60,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            modifier = Modifier.weight(1F)
                        ) {
                            CodeTextField(
                                value = uiState.code4,
                                onValueChange = { newValue ->
                                    viewModel.updateCode4(newValue)
                                    if (newValue.length > uiState.code4.length) {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    } else {
                                        focusManager.moveFocus(FocusDirection.Left)
                                    }
                                },
                                readOnly = readOnly >= 1,
                            )

                        }
                        AnimatedVisibility(
                            visible = code,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 70,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            modifier = Modifier.weight(1F)
                        ) {
                            CodeTextField(
                                value = uiState.code5,
                                onValueChange = { newValue ->
                                    viewModel.updateCode5(newValue)
                                    if (newValue.length > uiState.code5.length) {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    } else {
                                        focusManager.moveFocus(FocusDirection.Left)
                                    }
                                },
                                readOnly = readOnly >= 1,
                            )
                        }
                        AnimatedVisibility(
                            visible = code,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 80,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            modifier = Modifier.weight(1F)
                        ) {
                            CodeTextField(
                                value = uiState.code6,
                                onValueChange = { newValue ->
                                    viewModel.updateCode6(newValue)
                                    if (newValue.length <= uiState.code6.length) {
                                        focusManager.moveFocus(FocusDirection.Left)
                                    }
                                },
                                readOnly = readOnly >= 1,
                            )
                        }
                    }
                }

            }
        }
        BasicButton(
            text = "다음",
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            readOnly += 1
            viewModel.updateClicked(false)
            name = true
        }
    }
}


@Composable
@Preview
fun SignUpPreview() {
    SignUpScreen(
        navController = NavHostController(context = LocalContext.current)
    )
}