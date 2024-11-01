package com.artemklymenko.mychat.data

import com.artemklymenko.mychat.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        name: String,
        password: String
    ): Result<FirebaseUser> {
        return suspendCoroutine { continuation ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            val userInfo = mapOf(
                                "email" to email,
                                "username" to name,
                                "profileImage" to "",
                                "chats" to ""
                            )

                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(user.uid)
                                .setValue(userInfo)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        continuation.resume(Result.success(user))
                                    } else {
                                        continuation.resumeWithException(
                                            dbTask.exception ?: Exception("Database update failed")
                                        )
                                    }
                                }
                        } else {
                            continuation.resumeWithException(Exception("User is null after registration"))
                        }
                    } else {
                        continuation.resumeWithException(
                            task.exception ?: Exception("Registration failed")
                        )
                    }
                }
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}