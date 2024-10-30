package com.artemklymenko.mychat.presentation.pages.sign_in

sealed class SignInEvent {
    data object SignIn: SignInEvent()
    data class UpdateEmail(val newEmail: String): SignInEvent()
    data class UpdatePassword(val newPassword: String): SignInEvent()
    data object PasswordVisibility: SignInEvent()
}