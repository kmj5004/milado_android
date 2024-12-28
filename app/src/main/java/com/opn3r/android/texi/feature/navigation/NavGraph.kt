package com.opn3r.android.texi.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.opn3r.android.texi.feature.auth.Auth
import com.opn3r.android.texi.feature.auth.AuthScreen
import com.opn3r.android.texi.feature.auth.signup.SignUpScreen
import com.opn3r.android.texi.feature.main.MainScreen
import com.opn3r.android.texi.feature.splash.SplashScreen
import kotlinx.coroutines.MainScope

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavGroup.SPLASH) {
        composable( route = NavGroup.SPLASH ) {
            SplashScreen(navController = navHostController)
        }

        composable( route = NavGroup.MAIN) {
            MainScreen(navController = navHostController)
        }

        composable( route = NavGroup.AUTH) {
            AuthScreen(navController = navHostController)
        }

        composable( route = NavGroup.SIGNUP ) {
            SignUpScreen(navController = navHostController)
        }
    }
}