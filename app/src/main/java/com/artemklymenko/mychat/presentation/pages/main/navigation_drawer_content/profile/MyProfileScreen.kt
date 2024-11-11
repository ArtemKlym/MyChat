package com.artemklymenko.mychat.presentation.pages.main.navigation_drawer_content.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyProfileScreen() {
    Scaffold{
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "ProfileScreen")
        }
    }
}
