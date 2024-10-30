package com.artemklymenko.mychat.presentation.pages.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemklymenko.mychat.domain.model.RegisterInputValidationType
import com.artemklymenko.mychat.domain.repository.AuthRepository
import com.artemklymenko.mychat.domain.use_case.ValidateRegisterInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    var registerState by mutableStateOf(SignUpState())
        private set

    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.SignUp -> {
                onRegisterClick()
            }
            is SignUpEvent.UpdateUsername ->{
                onUsernameInputChange(event.newUsername)
            }
            is SignUpEvent.UpdateEmail -> {
                onEmailInputChange(event.newEmail)
            }
            is SignUpEvent.UpdatePassword -> {
                onPasswordInputChange(event.newPassword)
            }
            is SignUpEvent.UpdateRepeatedPassword -> {
                onPasswordRepeatedInputChange(event.newRepeatedPassword)
            }
            is SignUpEvent.PasswordVisibility -> {
                onToggleVisualTransformationPassword()
            }
            is SignUpEvent.PasswordRepeatedVisibility -> {
                onToggleVisualTransformationPasswordRepeated()
            }
        }
    }

    private fun onUsernameInputChange(newUsername: String) {
        registerState = registerState.copy(usernameInput = newUsername)
        checkInputValidation()
    }

    private fun onEmailInputChange(email: String) {
        registerState = registerState.copy(emailInput = email)
        checkInputValidation()
    }

    private fun onPasswordInputChange(password: String) {
        registerState = registerState.copy(passwordInput = password)
        checkInputValidation()
    }

    private fun onPasswordRepeatedInputChange(password: String) {
        registerState = registerState.copy(passwordRepeatedInput = password)
        checkInputValidation()
    }

    private fun onToggleVisualTransformationPassword() {
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordShown)
    }

    private fun onToggleVisualTransformationPasswordRepeated() {
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordRepeatedShown)
    }

    private fun onRegisterClick() {
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            registerState = try {
                val registerResult = authRepository.register(
                    email = registerState.emailInput,
                    password = registerState.passwordInput,
                    name = registerState.usernameInput
                )
                registerState.copy(
                    isSuccessfullyRegistered = registerResult.isSuccess,
                    isLoading = false
                )
            }catch (e: Exception) {
                registerState.copy(
                    errorMessageRegisterProcess = e.message,
                    isLoading = false
                )
            }
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(
            registerState.emailInput,
            registerState.passwordInput,
            registerState.passwordRepeatedInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType) {
        registerState = when (type) {
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(errorMessage = "Empty fields left", isInputValid = false)
            }
            RegisterInputValidationType.NoEmail -> {
                registerState.copy(errorMessage = "No valid email", isInputValid = false)
            }
            RegisterInputValidationType.EmailIsTooShort -> {
                registerState.copy(errorMessage = "Email is too short", isInputValid = false)
            }
            RegisterInputValidationType.PasswordIsTooShort -> {
                registerState.copy(errorMessage = "Password is too short", isInputValid = false)
            }
            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(errorMessage = "Password needs to contain at least one number", isInputValid = false)
            }
            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(errorMessage = "Password needs to contain at least one upper case char", isInputValid = false)
            }
            RegisterInputValidationType.PasswordSpecialCharMissing -> {
                registerState.copy(errorMessage = "Password needs to contain at least one special char", isInputValid = false)
            }
            RegisterInputValidationType.PasswordsDoNotMatch -> {
                registerState.copy(errorMessage = "Passwords do not match", isInputValid = false)
            }
            RegisterInputValidationType.Valid -> {
                registerState.copy(errorMessage = null, isInputValid = true)
            }
        }
    }
}