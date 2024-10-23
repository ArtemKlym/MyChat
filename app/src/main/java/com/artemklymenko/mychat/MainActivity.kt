package com.artemklymenko.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.artemklymenko.mychat.navigation.RootAppNavigation
import com.artemklymenko.mychat.ui.theme.MyChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyChatTheme {
                RootAppNavigation()
            }
        }
    }
}