package com.artemklymenko.mychat.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String) : Result<FirebaseUser>

    suspend fun register(email: String, name: String, password: String) : Result<FirebaseUser>

    fun logout()
}