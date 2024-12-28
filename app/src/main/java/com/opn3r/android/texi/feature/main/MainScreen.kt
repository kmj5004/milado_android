package com.opn3r.android.texi.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(color = Color.White)
    )
}

