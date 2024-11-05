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
    data object MainPage: Routes("main")
    data object MyProfile: Routes("my_profile")
    data object NewGroup: Routes("new_group")
    data object Settings: Routes("settings")
    data object Calls: Routes("calls")
    data object Contacts: Routes("contacts")
    data object Chats: Routes("chats")
    data object SavedMessages: Routes("saved_messages")
}