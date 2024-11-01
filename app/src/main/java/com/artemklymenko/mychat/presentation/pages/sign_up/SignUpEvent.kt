package com.artemklymenko.mychat.presentation.pages.sign_up

sealed class SignUpEvent {
    data object SignUp: SignUpEvent()
    data class UpdateEmail(val newEmail: String): SignUpEvent()
    data class UpdateUsername(val newUsername: String): SignUpEvent()
    data class UpdatePassword(val newPassword: String): SignUpEvent()
    data class UpdateRepeatedPassword(val newRepeatedPassword: String): SignUpEvent()
    data object PasswordVisibility: SignUpEvent()
    data object PasswordRepeatedVisibility: SignUpEvent()
}