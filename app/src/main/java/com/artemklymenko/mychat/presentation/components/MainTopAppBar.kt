package com.artemklymenko.mychat.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import com.artemklymenko.mychat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(onMenuClick: () -> Unit) {
    var searchMode by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }
    val rotationAngle by animateFloatAsState(targetValue = if (searchMode) 360f else 0f, label = "")

    if (searchMode) {
        TopAppBar(
            title = {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text(text = stringResource(R.string.search)) },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(onClick = { searchMode = false }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier.graphicsLayer(rotationZ = rotationAngle)
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(R.string.menu),
                        modifier = Modifier.graphicsLayer(rotationZ = rotationAngle)
                    )
                }
            },
            actions = {
                IconButton(onClick = { searchMode = true }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            }
        )
    }
}

