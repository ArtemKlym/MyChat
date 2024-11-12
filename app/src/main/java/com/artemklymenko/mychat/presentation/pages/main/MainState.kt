package com.artemklymenko.mychat.presentation.pages.main

import com.artemklymenko.mychat.domain.model.ChatItem
import com.google.firebase.auth.FirebaseUser

data class MainState(
    val user: FirebaseUser? = null,
    val chats: List<ChatItem>? = emptyList()
)
