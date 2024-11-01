package com.artemklymenko.mychat.presentation.pages.splash

sealed class SplashEvent {
    data object CheckIsUserAuth: SplashEvent()
}