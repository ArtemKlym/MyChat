package com.artemklymenko.mychat.presentation.pages.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(modifier: Modifier) {
    Scaffold {
        MainScreenContent(
            modifier = modifier.padding(it)
        )
    }
}

@Composable
fun MainScreenContent(modifier: Modifier) {
    Column {
        Text(text = "MainScreen")
    }
}
