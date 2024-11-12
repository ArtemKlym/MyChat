package com.artemklymenko.mychat.presentation.pages.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemklymenko.mychat.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){

    var mainState by mutableStateOf(MainState())
        private set

    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.LoadUser -> {
                loadUser()
            }
            is MainEvent.LoadChats -> {
                loadChats()
            }
        }
    }

    private fun loadChats() {
        TODO("tbd")
    }

    private fun loadUser() {
        mainState = mainState.copy(user = authRepository.currentUser)
    }

}