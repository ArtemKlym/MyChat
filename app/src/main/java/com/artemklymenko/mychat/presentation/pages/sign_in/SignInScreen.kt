package com.artemklymenko.mychat.presentation.pages.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
fun SignInScreen(
    modifier: Modifier,
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Scaffold(
        modifier = modifier, content = {
            LoginScreenContent(
                Modifier.padding(it),
                state,
                onEvent,
                onForgotPasswordClick,
                onRegisterClick
            )
        }
    )
    LaunchedEffect(state.isSuccessfullyLoggedIn) {
        if (state.isSuccessfullyLoggedIn) {
            onLoginClick()
        }
    }
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier,
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Image(
            modifier = Modifier
                .size(256.dp)
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.login),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 24.dp)
        )
        TextField(
            value = state.emailInput,
            onValueChange = { newEmail ->
                onEvent(SignInEvent.UpdateEmail(newEmail))
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
                .padding(vertical = 16.dp)
        )
        TextField(
            value = state.passwordInput,
            onValueChange = { newPassword ->
                onEvent(SignInEvent.UpdatePassword(newPassword))
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
                    modifier = Modifier.clickable { onEvent(SignInEvent.PasswordVisibility) }
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
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                modifier = Modifier
                    .clickable { onForgotPasswordClick() }
            )
        }
        Button(
            onClick = {
                onEvent(SignInEvent.SignIn)
            },
            enabled = state.isInputValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
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
                Text(text = stringResource(R.string.login))
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.new_to_mychat)
            )
            Text(
                text = stringResource(R.string.sign_up),
                color = Color(0xFF0EA9F8),
                modifier = Modifier
                    .clickable {
                        onRegisterClick()
                    }
            )
        }
    }
}


@Preview
@Composable
private fun SignInScreenPreviewLight() {
    MyChatTheme {
        SignInScreen(
            modifier = Modifier,
            state = SignInState(),
            onEvent = { SignInEvent.SignIn },
            onForgotPasswordClick = { /*TODO*/ },
            onLoginClick = { /*TODO*/ }) {

        }
    }
}

@Preview
@Composable
private fun SignInScreenPreviewDark() {
    MyChatTheme(darkTheme = true) {
        SignInScreen(
            modifier = Modifier,
            state = SignInState(),
            onEvent = {},
            onForgotPasswordClick = { /*TODO*/ },
            onLoginClick = { /*TODO*/ }) {

        }
    }
}