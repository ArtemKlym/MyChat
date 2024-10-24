package com.artemklymenko.mychat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemklymenko.mychat.pages.sign_in.SignInScreen
import com.artemklymenko.mychat.pages.sign_up.SignUpScreen
import com.artemklymenko.mychat.pages.splash.SplashScreen

@Composable
fun RootAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Splash.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Splash.route){
            SplashScreen(
                modifier = modifier,
                onNavigateToSignIn = {
                    navController.navigate(Routes.SignIn.route){
                        popUpTo(0)
                    }
                }
            )
        }

        composable(Routes.SignIn.route){
            SignInScreen(
                modifier = modifier,
                onForgotPasswordClick = {},
                onLoginClick = {}) {
                navController.navigate(Routes.SignUp.route)
            }
        }

        composable(Routes.SignUp.route){
            SignUpScreen(
                modifier = modifier,
                onRegisterClick = {}
            ) {
                navController.navigate(Routes.SignIn.route){
                    popUpTo(0)
                }
            }
        }
    }
}