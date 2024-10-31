package com.artemklymenko.mychat.presentation.navigation

sealed class Routes(val route: String) {
    /**
     * Entry point
     */
    data object Splash: Routes("splash")
    /**
     * Authentication
     */
    data object SignIn: Routes("sign_in")
    data object SignUp: Routes("sign_up")
    /**
     * Main screen
     */
    data object MainPaige: Routes("main")
}