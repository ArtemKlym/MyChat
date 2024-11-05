package com.artemklymenko.mychat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemklymenko.mychat.presentation.pages.main.CallsScreen
import com.artemklymenko.mychat.presentation.pages.main.ContactsScreen
import com.artemklymenko.mychat.presentation.pages.main.MainScreen
import com.artemklymenko.mychat.presentation.pages.main.MainViewModel
import com.artemklymenko.mychat.presentation.pages.main.MyProfileScreen
import com.artemklymenko.mychat.presentation.pages.main.NewGroupScreen
import com.artemklymenko.mychat.presentation.pages.main.SavedMessagesScreen
import com.artemklymenko.mychat.presentation.pages.main.SettingsScreen
import com.artemklymenko.mychat.presentation.pages.sign_in.SignInScreen
import com.artemklymenko.mychat.presentation.pages.sign_in.SignInViewModel
import com.artemklymenko.mychat.presentation.pages.sign_up.SignUpScreen
import com.artemklymenko.mychat.presentation.pages.sign_up.SignUpViewModel
import com.artemklymenko.mychat.presentation.pages.splash.SplashScreen
import com.artemklymenko.mychat.presentation.pages.splash.SplashViewModel

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
            val viewModel = hiltViewModel<SplashViewModel>()
            val state = viewModel.splashState
            SplashScreen(
                modifier = modifier,
                state = state,
                onEvent = viewModel::onEvent,
                onNavigateToSignIn = {
                    navController.navigate(Routes.SignIn.route){
                        popUpTo(0)
                    }
                },
                onNavigateToMainScreen = {
                    navController.navigate(Routes.MainPage.route){
                        popUpTo(0)
                    }
                }
            )
        }

        composable(Routes.SignIn.route){
            val viewModel = hiltViewModel<SignInViewModel>()
            val state = viewModel.loginState
            SignInScreen(
                modifier = modifier,
                state = state,
                onEvent = viewModel::onEvent,
                onForgotPasswordClick = {},
                onLoginClick = {
                    navController.navigate(Routes.MainPage.route){
                        popUpTo(0)
                    }
                }
            ) {
                navController.navigate(Routes.SignUp.route)
            }
        }

        composable(Routes.SignUp.route){
            val viewModel = hiltViewModel<SignUpViewModel>()
            val state = viewModel.registerState
            SignUpScreen(
                modifier = modifier,
                state = state,
                onEvent = viewModel::onEvent,
                onRegisterClick = {
                    navController.navigate(Routes.SignIn.route){
                        popUpTo(0)
                    }
                }
            ) {
                navController.navigate(Routes.SignIn.route){
                    popUpTo(0)
                }
            }
        }
        composable(Routes.MainPage.route){
            val viewModel = hiltViewModel<MainViewModel>()
            val state = viewModel.mainState
            MainScreen(
                modifier = modifier,
                state = state,
                onEvent = viewModel::onEvent,
                onProfileClick = {
                    navController.navigate(Routes.MyProfile.route) {
                        popUpTo(Routes.MainPage.route) { inclusive = false }
                        restoreState = true
                    }
                },
                onContactsClick = {
                    navController.navigate(Routes.Contacts.route) {
                        popUpTo(Routes.MainPage.route) { inclusive = false }
                        restoreState = true
                    }
                },
                onNewGroupClick = {
                    navController.navigate(Routes.NewGroup.route) {
                        popUpTo(Routes.MainPage.route) { inclusive = false }
                        restoreState = true
                    }
                },
                onCallsClick = {
                    navController.navigate(Routes.Calls.route) {
                        popUpTo(Routes.MainPage.route) { inclusive = false }
                        restoreState = true
                    }
                },
                onSavedMessagesClick = {
                    navController.navigate(Routes.SavedMessages.route) {
                        popUpTo(Routes.MainPage.route) { inclusive = false }
                        restoreState = true
                    }
                },
                onSettingsClick = {
                    navController.navigate(Routes.Settings.route) {
                        popUpTo(Routes.MainPage.route) { inclusive = false }
                        restoreState = true
                    }
                }
            )
        }
        composable(Routes.MyProfile.route) {
            MyProfileScreen()
        }
        composable(Routes.NewGroup.route) {
            NewGroupScreen()
        }
        composable(Routes.Settings.route) {
            SettingsScreen()
        }
        composable(Routes.Calls.route) {
            CallsScreen()
        }
        composable(Routes.SavedMessages.route) {
            SavedMessagesScreen()
        }
        composable(Routes.Contacts.route) {
            ContactsScreen()
        }
    }
}