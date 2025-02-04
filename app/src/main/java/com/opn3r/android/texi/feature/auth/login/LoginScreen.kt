package com.opn3r.android.texi.feature.auth.login

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.opn3r.android.texi.R
import com.opn3r.android.texi.ui.compnent.button.BasicButton
import com.opn3r.android.texi.ui.compnent.button.CircleButton
import com.opn3r.android.texi.ui.compnent.textfield.DeleteTextField
import com.opn3r.android.texi.ui.compnent.textfield.PasswordTextField
import com.opn3r.android.texi.feature.font.notSanskrit

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val string = buildAnnotatedString {
        append("계정을 잃으셨나요?")
        withStyle(
            SpanStyle(
                color = colorResource(R.color.text_blue)
            )
        ) {
            append(" ID 찾기 ")
        }
        append("또는")
        withStyle(
            SpanStyle(
                color = colorResource(R.color.text_blue)
            )
        ) {
            append(" 비밀번호 찾기")
        }
    }
    val count = remember { mutableIntStateOf(0) }
    val loginCount = remember { mutableStateOf(true) }


    if (count.intValue >= 10) {
        loginCount.value = false
        Log.d("count", "LoginScreen: ${count.intValue}")
    }



    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 30.dp)
            .padding(top = 30.dp)
    ) {
        Column(
            modifier = modifier
                .padding(top = 60.dp)
        ) {
            AnimatedVisibility(
                visible = loginCount.value,
                enter = slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth }
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth - 200 },
                )
            ) {
                Text(
                    text = "함께타는\n택시의\n첫걸음",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = notSanskrit,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                )
            }
        }
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    modifier = modifier
                        .size(20.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = "로그인",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 90.sp,
                        fontFamily = notSanskrit,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF191919),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
            Spacer(Modifier.height(30.dp))
            Box(
                modifier = modifier
                    .animateContentSize()
                    .height(if (loginCount.value) 130.dp else 0.dp)
            )
            DeleteTextField(
                value = uiState.id,
                onValueChange = viewModel::updateId,
                hint = "아이디 또는 비밀번호를 입력해 주세요",
                onClick = {
                    viewModel.updateId("")
                },
                error = uiState.error,
                readOnly = false
            )
            Spacer(modifier = modifier.height(10.dp))
            PasswordTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                hint = "비밀번호를 입력해 주세요",
                onClick = {
                    viewModel.updateId("")
//                    viewModel.updateError(error = !uiState.error)
                    Log.d("error", "LoginScreen: ${uiState.error}")
                },
                readOnly = false,
                error = uiState.error
            )
            Spacer(Modifier.height(10.dp))
                Column(
                    modifier = modifier
                        .animateContentSize()
                        .height(if (loginCount.value) 0.dp else 100.dp)
                ) {
                    Text(
                        text = "로그인 실패 횃수가 10회 초과되었습니다.",
                        fontSize = 14.sp,
                        color = colorResource(R.color.error_red)
                    )
                    Text(
                        text = "계정을 안전하게 보호하기 위해 잠김 처리가 되었습니다.\n비밀번호 찾기를 통한 재설정 후 로그인 가능합니다.",
                        fontSize = 12.sp,
                        color = colorResource(R.color.mid_gray_text)
                    )
                    Spacer(Modifier.height(20.dp))
                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = colorResource(R.color.low_gray_line))
                    )
                    Spacer(Modifier.height(10.dp))
                }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleButton(
                    click = uiState.autoLogin
                ) {
                    viewModel.updateAutoLogin(!uiState.autoLogin)
                }
                Spacer(Modifier.width(5.dp))
                Text(
                    text = "자동 로그인",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 40.sp,
                        fontFamily = notSanskrit
                    ),
                    color = Color(0xFF000000),
                    letterSpacing = 0.25.sp,
                )
                Spacer(Modifier.width(15.dp))
                CircleButton(
                    click = uiState.saveId
                ) {
                    viewModel.updateSaveId(!uiState.saveId)
                }
                Spacer(Modifier.width(5.dp))
                Text(
                    text = "아이디 저장",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 40.sp,
                        fontFamily = notSanskrit,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
            Spacer(Modifier.height(40.dp))
            BasicButton(
                onClick = {
                    count.value += 1
                },
                text = if (loginCount.value) "로그인" else "비밀번호 변경"
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = string,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            Text(
                text = "회원이 아니신가요?"
            )
            Spacer(Modifier.width(3.dp))
            Text(
                text = "회원가입",
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .clickable {

                    }
            )
        }
    }
}


@Composable
@Preview
fun LoginPreview() {
    LoginScreen(
        navController = NavHostController(context = LocalContext.current)
    )
}


