package com.opn3r.android.texi.feature.auth.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.opn3r.android.texi.R
import com.opn3r.android.texi.ui.compnent.button.CircleButton
import com.opn3r.android.texi.ui.compnent.textfield.DeleteTextField
import com.opn3r.android.texi.ui.compnent.textfield.PasswordTextField
import notoSanskr

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val error by remember { mutableStateOf(false) }

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
            Spacer(Modifier.height(30.dp))
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
                Spacer(modifier = modifier.height(20.dp))
                DeleteTextField(
                    value = uiState.id,
                    onValueChange = viewModel::updateId,
                    hint = "아이디 또는 비밀번호를 입력해 주세요",
                    onClick = {
                        viewModel.updateId("")
                    },
                    readOnly = false
                )
                Spacer(modifier = modifier.height(10.dp))
                PasswordTextField(
                    value = uiState.password,
                    onValueChange = viewModel::updatePassword,
                    hint = "비밀번호를 입력해 주세요",
                    onClick = {
                        viewModel.updateId("")
                        error != error
                        Log.d("error", "LoginScreen: $error")
                    },
                    readOnly = false,
                    error = error
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleButton(
                        click = uiState.autoLogin
                    ) {
                        viewModel.updateAutoLogin(!uiState.autoLogin)
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "자동 로그인",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 40.sp,
                            fontFamily = notoSanskr
                        ),
                        color = Color(0xFF000000),
                        letterSpacing = 0.25.sp,
                    )
                    Spacer(Modifier.width(20.dp))
                    CircleButton(
                        click = uiState.saveId
                    ) {
                        viewModel.updateSaveId(!uiState.saveId)
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "아이디 저장",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 40.sp,
                            fontFamily = notoSanskr,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            letterSpacing = 0.25.sp,
                        )
                    )
                }
            }
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


