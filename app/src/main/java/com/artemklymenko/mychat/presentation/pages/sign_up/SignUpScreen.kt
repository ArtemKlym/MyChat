package com.artemklymenko.mychat.presentation.pages.sign_up

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.artemklymenko.mychat.presentation.ui.theme.MyChatTheme

@Composable
fun SignUpScreen(
    modifier: Modifier,
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) {
        SignUpScreenContent(
            modifier = Modifier.padding(it),
            state = state,
            onEvent = onEvent,
            onRegisterClick = onRegisterClick
        )
    }
    LaunchedEffect(state.isSuccessfullyRegistered) {
        if (state.isSuccessfullyRegistered) {
            onRegisterClick()
        }
    }
    BackHandler {
        onBackClick()
    }
}

@Composable
private fun SignUpScreenContent(
    modifier: Modifier,
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState())
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
            value = state.emailInput,
            onValueChange = { newEmail ->
                onEvent(SignUpEvent.UpdateEmail(newEmail))
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
            isError = state.errorMessage != null,
            supportingText = {
                Text(text = state.errorMessage ?: "")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        TextField(
            value = state.usernameInput,
            onValueChange = { newUsername ->
                onEvent(SignUpEvent.UpdateUsername(newUsername))
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
            isError = state.errorMessage != null,
            supportingText = {
                Text(text = state.errorMessage ?: "")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextField(
            value = state.passwordInput,
            onValueChange = { newPassword ->
                onEvent(SignUpEvent.UpdatePassword(newPassword))
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
                    imageVector = if (state.isPasswordShown)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                    contentDescription = null,
                    modifier = Modifier.clickable { onEvent(SignUpEvent.PasswordVisibility) }
                )
            },
            isError = state.errorMessage != null,
            supportingText = {
                Text(text = state.errorMessage ?: "")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            visualTransformation = if (state.isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        TextField(
            value = state.passwordRepeatedInput,
            onValueChange = { newPassword ->
                onEvent(SignUpEvent.UpdateRepeatedPassword(newPassword))
            },
            placeholder = {
                Text(text = stringResource(R.string.confirm_password))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = if (state.isPasswordShown)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                    contentDescription = null,
                    modifier = Modifier.clickable { onEvent(SignUpEvent.PasswordRepeatedVisibility) }
                )
            },
            isError = state.errorMessage != null,
            supportingText = {
                Text(text = state.errorMessage ?: "")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            visualTransformation = if (state.isPasswordRepeatedShown) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(56.dp))
        Button(
            onClick = {
                onEvent(SignUpEvent.SignUp)
            },
            enabled = state.isInputValid,
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(text = stringResource(R.string.sign_up))
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreviewDark() {
    MyChatTheme(darkTheme = true) {
        SignUpScreen(
            modifier = Modifier,
            state = SignUpState(),
            onEvent = {},
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
            state = SignUpState(),
            onEvent = {},
            onRegisterClick = {}
        ) {}
    }
}
