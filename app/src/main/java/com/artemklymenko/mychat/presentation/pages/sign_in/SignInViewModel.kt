package com.artemklymenko.mychat.presentation.pages.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemklymenko.mychat.domain.model.LoginInputValidationType
import com.artemklymenko.mychat.domain.repository.AuthRepository
import com.artemklymenko.mychat.domain.use_case.ValidateLoginInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    var loginState by mutableStateOf(SignInState())
        private set

    fun onEvent(event: SignInEvent){
        when(event){
            is SignInEvent.SignIn -> {
                onLoginClick()
            }
            is SignInEvent.UpdateEmail -> {
                onEmailInputChange(event.newEmail)
            }
            is SignInEvent.UpdatePassword -> {
                onPasswordInputChange(event.newPassword)
            }
            is SignInEvent.PasswordVisibility -> {
                onToggleVisualTransformation()
            }
        }
    }

    private fun onEmailInputChange(email: String) {
        loginState = loginState.copy(emailInput = email)
        checkInputValidation()
    }

    private fun onPasswordInputChange(password: String) {
        loginState = loginState.copy(passwordInput = password)
        checkInputValidation()
    }

    private fun onToggleVisualTransformation() {
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    private fun onLoginClick() {
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            loginState = try {
                val loginResult = authRepository.login(
                    email = loginState.emailInput,
                    password = loginState.passwordInput
                )
                loginState.copy(
                    isSuccessfullyLoggedIn = loginResult.isSuccess,
                    isLoading = false
                )
            }catch (e: Exception) {
                loginState.copy(
                    errorMessageLoginProcess = e.message,
                    isLoading = false
                )
            }
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        loginState = when (type) {
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessage = "Empty fields left", isInputValid = false)
            }
            LoginInputValidationType.NoEmail -> {
                loginState.copy(errorMessage = "No valid email", isInputValid = false)
            }
            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessage = null, isInputValid = true)
            }
        }
    }
}