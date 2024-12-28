package com.opn3r.android.texi.feature.auth

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.opn3r.android.texi.R
import com.opn3r.android.texi.ui.compnent.button.BasicButton
import notoSanskr
import com.google.accompanist.permissions.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.opn3r.android.texi.feature.navigation.NavGroup
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    )

    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    var herefusesagain by remember { mutableStateOf(false) }

    val context = LocalContext.current

    var pass by remember { mutableStateOf(false) }

    var snackBar by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val snackBarHost = remember { SnackbarHostState() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Column {
            Spacer(Modifier.height(50.dp))
            Text(
                text = "앱 이용을 위해 \n아래 접근 권한 허용이 필요해요",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 32.sp,
                    fontFamily = notoSanskr,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    letterSpacing = 0.25.sp,
                )
            )
            Spacer(Modifier.height(15.dp))
            Text(
                text = "접근 권한 요청을 받으면 허용해 주세요",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = notoSanskr,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF999999),
                    letterSpacing = 0.25.sp,
                )
            )
            Spacer(Modifier.height(35.dp))
            Auth(
                image = painterResource(R.drawable.vector_1),
                title = "위치",
                content = "지도 탐색 등 위치기반 서비스 이용시"
            )
            Spacer(Modifier.height(20.dp))
            Auth(
                image = painterResource(R.drawable.icon),
                title = "알림",
                content = "채팅 알림 또는 택시 탑승 시간 수신시"
            )
            Spacer(Modifier.height(20.dp))
            Auth(
                image = painterResource(R.drawable.vector_2),
                title = "사진",
                content = "프로필 사진 업로드시"
            )
        }
        SnackbarHost(hostState = snackBarHost)
        LaunchedEffect(snackBar) {
            Log.d("스낵바", "AuthScreen: $snackBar")
            if (snackBar) {
                coroutineScope.launch {
                    val snackBar =
                        snackBarHost.showSnackbar(
                            "설정에서 권한을 허용해주세요.",
                            "설정 화면으로 이동"
                        )
                    when (snackBar) {
                        SnackbarResult.ActionPerformed -> {
                            val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts("package", context.packageName, null)
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                            } else {
                                Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", context.packageName, null)
                                )
                            }
                            context.startActivity(intent)
                        }

                        SnackbarResult.Dismissed -> {

                        }
                    }
                }
            }
        }
        BasicButton(
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 15.dp),
            text = "시작하기",
            onClick = {
                if (pass) {
                    if (!locationPermissionState.status.isGranted) {
                        locationPermissionState.launchPermissionRequest()
                        Toast.makeText(context, "서비스를 이용하시기 위한 필수 권한 입니다", Toast.LENGTH_SHORT)
                            .show()
                        herefusesagain = true
                    } else {
                        navController.navigate(NavGroup.SIGNUP)
                    }
                    if (!locationPermissionState.status.isGranted && herefusesagain) {
                        snackBar = true
                    }
                } else {
                    permissionsState.launchMultiplePermissionRequest()
                    pass = true
                }
            }
        )
    }
}


@Composable
fun Auth(
    modifier: Modifier = Modifier,
    image: Painter,
    title: String,
    content: String
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
        ) {
            Box(
                modifier = modifier
                    .size(40.dp)
                    .background(color = Color(0xFFF8F8FA), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = modifier
                        .size(22.dp)
                )
            }
            Spacer(Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = notoSanskr,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    )
                )
                Text(
                    text = content,
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 40.sp,
                        fontFamily = notoSanskr,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF767676),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
        }
    }
}


@Composable
@Preview
fun AuthPreview() {
    AuthScreen(
        navController = NavHostController(context = LocalContext.current)
    )
}