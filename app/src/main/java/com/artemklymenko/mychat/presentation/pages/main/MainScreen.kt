package com.artemklymenko.mychat.presentation.pages.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemklymenko.mychat.R
import com.artemklymenko.mychat.domain.model.ChatItem
import com.artemklymenko.mychat.presentation.components.MainTopAppBar
import com.artemklymenko.mychat.presentation.components.NavigationDrawerContent
import com.artemklymenko.mychat.presentation.navigation.NavigationItem
import com.artemklymenko.mychat.presentation.utils.formatDate
import kotlinx.coroutines.launch
import java.util.Date


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
    onSettingsClick: () -> Unit,
    onAddChatClick: () -> Unit,
    onChatClick: (Int) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(-1) }

    val items = listOf(
        NavigationItem(
            stringResource(R.string.my_profile),
            Icons.Filled.Person,
            Icons.Outlined.Person
        ),
        NavigationItem(
            stringResource(R.string.new_group),
            Icons.Filled.Group,
            Icons.Outlined.Group
        ),
        NavigationItem(
            stringResource(R.string.contacts),
            Icons.Filled.Contacts,
            Icons.Outlined.Contacts
        ),
        NavigationItem(stringResource(R.string.calls), Icons.Filled.Call, Icons.Outlined.Call),
        NavigationItem(
            stringResource(R.string.saved_messages),
            Icons.Filled.Bookmark,
            Icons.Outlined.Bookmark
        ),
        NavigationItem(
            stringResource(R.string.settings),
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            Icons.Filled.Error
        )
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
            MainScreenContent(
                modifier = Modifier.padding(padding),
                state = state,
                onEvent = onEvent,
                onAddChatClick = onAddChatClick,
                onChatClick = onChatClick
            )
        }
    }
}


@Composable
private fun MainScreenContent(
    modifier: Modifier,
    state: MainState,
    onEvent: (MainEvent) -> Unit,
    onAddChatClick: () -> Unit,
    onChatClick: (Int) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {

        if (state.chats != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.chats) { chat ->
                    ChatItemFragment(
                        chat = chat,
                        onChatClick = onChatClick
                    )
                }
            }
        } else {
            Image(
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.no_chats_found),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        FloatingActionButton(
            onClick = onAddChatClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clip(CircleShape)
        ) {
            Image(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun ChatItemFragment(
    chat: ChatItem,
    onChatClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChatClick(chat.id)}
    ) {
        AsyncImage(
            model = chat.image,
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .padding(8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = chat.name,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = formatDate(chat.data),
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chat.message,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (chat.amount > 0) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${chat.amount}",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        state = MainState(
            chats = listOf(ChatItem(
                id = 0,
                name = "Jack",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDzn36DbfZjqNe6vVsjGJidTdAaMbTWqC6ug&s",
                message = "Hello!",
                amount = 1,
                data = Date()
            ))
        ),
        onEvent = {},
        onProfileClick = {},
        onNewGroupClick = {},
        onContactsClick = {},
        onCallsClick = {},
        onSavedMessagesClick = {},
        onAddChatClick = {},
        onSettingsClick = {},
        onChatClick = {})
}
