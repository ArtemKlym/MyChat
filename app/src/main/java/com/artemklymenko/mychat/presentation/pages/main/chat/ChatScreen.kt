package com.artemklymenko.mychat.presentation.pages.main.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(
    chatId: Int,
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {}
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "ChatScreen"
        )
    }
}