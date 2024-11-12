package com.artemklymenko.mychat.domain.model

import java.util.Date

data class ChatItem(
    val id: Int,
    val name: String,
    val image: String,
    val message: String,
    val amount: Int,
    val data: Date,
)