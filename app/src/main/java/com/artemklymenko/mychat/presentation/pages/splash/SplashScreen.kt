package com.artemklymenko.mychat.presentation.pages.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.mychat.R
import com.artemklymenko.mychat.presentation.ui.theme.MyChatTheme

@Composable
fun SplashScreen(
    modifier: Modifier,
    onNavigateToSignIn: () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier.size(128.dp),
                painter = painterResource(id = R.drawable.message_image),
                contentDescription = null
            )
            Spacer(modifier = modifier.height(16.dp))
            CircularProgressIndicator()
            LaunchedEffect(key1 = Unit) {
                onNavigateToSignIn()
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreviewLight(){
    MyChatTheme {
        SplashScreen(modifier = Modifier, onNavigateToSignIn = {})
    }
}

@Preview
@Composable
private fun SplashScreenPreviewDark(){
    MyChatTheme(darkTheme = true) {
        SplashScreen(modifier = Modifier, onNavigateToSignIn = {})
    }
}