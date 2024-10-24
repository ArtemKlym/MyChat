package com.artemklymenko.mychat.pages.sign_up

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemklymenko.mychat.R
import com.artemklymenko.mychat.ui.theme.MyChatTheme

@Composable
fun SignUpScreen(
    modifier: Modifier,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) {
       SignUpScreenContent(
           modifier = Modifier.padding(it),
           onRegisterClick = onRegisterClick
       )
    }
    BackHandler {
        onBackClick()
    }
}

@Composable
private fun SignUpScreenContent(
    modifier: Modifier,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Image(
            modifier = Modifier
                .size(256.dp)
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.registration_image),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.sign_up),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
        )
        TextField(
            value = email,
            onValueChange = { text ->
                email = text.trim()
            },
            placeholder = {
                Text(text = stringResource(R.string.email))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        TextField(
            value = username,
            onValueChange = { text ->
                username = text.trim()
            },
            placeholder = {
                Text(text = stringResource(R.string.username))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        TextField(
            value = password,
            onValueChange = { text ->
                password = text.trim()
            },
            placeholder = {
                Text(text = stringResource(R.string.password))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                    contentDescription = null,
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(56.dp))
        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreviewDark() {
    MyChatTheme(darkTheme = true) {
        SignUpScreen(
            modifier = Modifier,
            onRegisterClick = {}
        ) {}
    }
}

@Preview
@Composable
private fun SignUpScreenPreviewLight() {
    MyChatTheme(darkTheme = false) {
        SignUpScreen(
            modifier = Modifier,
            onRegisterClick = {}
        ) {}
    }
}
