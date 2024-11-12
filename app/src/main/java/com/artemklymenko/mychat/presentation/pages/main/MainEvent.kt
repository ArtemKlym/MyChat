package com.artemklymenko.mychat.presentation.pages.main

sealed class MainEvent {
    data object LoadUser: MainEvent()
    data object LoadChats: MainEvent()
}