package com.artemklymenko.mychat.presentation.components

import android.content.res.Resources
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemklymenko.mychat.R
import com.artemklymenko.mychat.presentation.navigation.NavigationItem
import com.artemklymenko.mychat.presentation.pages.main.MainState

@Composable
fun NavigationDrawerContent(
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier,
    state: MainState,
    onProfileClick: () -> Unit,
    onNewGroupClick: () -> Unit,
    onContactsClick: () -> Unit,
    onCallsClick: () -> Unit,
    onSavedMessagesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(8.dp))
        ProfileDrawer(state = state)

        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = index == selectedItemIndex,
                onClick = {
                    onItemSelected(index)
                    when (index) {
                        0 -> onProfileClick()
                        1 -> onNewGroupClick()
                        2 -> onSettingsClick()
                        3 -> onCallsClick()
                        4 -> onSavedMessagesClick()
                        5 -> onContactsClick()
                        else -> onProfileClick()
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                badge = item.badgeIcon?.let {
                    { Icon(imageVector = it, contentDescription = null) }
                },
                modifier = modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
private fun ProfileDrawer(state: MainState) {
    var darkTheme by remember{ mutableStateOf(false) }
    var showAccounts by remember{ mutableStateOf(false) }
    val rotationAngleTheme by animateFloatAsState(targetValue = if (darkTheme) 360f else 0f, label = "")
    val rotationAngleAccounts by animateFloatAsState(targetValue = if (showAccounts) 180f else 0f, label = "")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDzn36DbfZjqNe6vVsjGJidTdAaMbTWqC6ug&s",
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )
            }
            IconButton(onClick = { darkTheme = !darkTheme }) {
                Icon(
                    imageVector = if (darkTheme) {
                        Icons.Filled.DarkMode
                    } else Icons.Filled.LightMode,
                    contentDescription = "System Theme",
                    modifier = Modifier
                        .size(32.dp)
                        .graphicsLayer(rotationZ = rotationAngleTheme),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Jack",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
                Text(
                    text = "+3800000000",
                    fontWeight = FontWeight.Light,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            }
            IconButton(onClick = { showAccounts = !showAccounts }) {
                Icon(
                    imageVector = if (showAccounts) {
                        Icons.Default.KeyboardArrowDown
                    } else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Show accounts",
                    modifier = Modifier
                        .size(40.dp)
                        .graphicsLayer(rotationZ = rotationAngleAccounts)
                )
            }
        }
    }
}