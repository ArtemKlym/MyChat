package com.artemklymenko.mychat.presentation.pages.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.artemklymenko.mychat.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var splashState by mutableStateOf(SplashState())
        private set

    fun onEvent(event: SplashEvent) {
        if (event == SplashEvent.CheckIsUserAuth) {
            checkIsUserAuthorized()
        }
    }

    private fun checkIsUserAuthorized() {
        splashState = if (authRepository.currentUser != null) {
            splashState.copy(hasUser = true)
        } else {
            splashState.copy(hasUser = false)
        }
    }
}