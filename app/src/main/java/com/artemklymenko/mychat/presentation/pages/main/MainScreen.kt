package com.artemklymenko.mychat.presentation.pages.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemklymenko.mychat.R
import com.artemklymenko.mychat.presentation.components.MainTopAppBar
import com.artemklymenko.mychat.presentation.components.NavigationDrawerContent
import com.artemklymenko.mychat.presentation.navigation.NavigationItem
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainState,
    onEvent: (MainEvent) -> Unit,
    onProfileClick: () -> Unit,
    onNewGroupClick: () -> Unit,
    onContactsClick: () -> Unit,
    onCallsClick: () -> Unit,
    onSavedMessagesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(-1) }

    val items = listOf(
        NavigationItem(stringResource(R.string.my_profile), Icons.Filled.Person, Icons.Outlined.Person),
        NavigationItem(stringResource(R.string.new_group), Icons.Filled.Group, Icons.Outlined.Group),
        NavigationItem(stringResource(R.string.contacts), Icons.Filled.Contacts, Icons.Outlined.Contacts),
        NavigationItem(stringResource(R.string.calls), Icons.Filled.Call, Icons.Outlined.Call),
        NavigationItem(stringResource(R.string.saved_messages), Icons.Filled.Bookmark, Icons.Outlined.Bookmark),
        NavigationItem(stringResource(R.string.settings), Icons.Filled.Settings, Icons.Outlined.Settings, Icons.Filled.Error)
    )

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawerContent(
                items = items,
                state = state,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = { index ->
                    selectedItemIndex = index
                    scope.launch { drawerState.close() }
                },
                modifier = modifier,
                onProfileClick = onProfileClick,
                onNewGroupClick = onNewGroupClick,
                onContactsClick = onContactsClick,
                onCallsClick = onCallsClick,
                onSavedMessagesClick = onSavedMessagesClick,
                onSettingsClick = onSettingsClick
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { padding ->
            MainScreenContent(modifier = Modifier.padding(padding))
        }
    }
}



@Composable
private fun MainScreenContent(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "MainScreen")
    }
}

@Preview
@Composable
private fun MainScreenPreview() {

}
