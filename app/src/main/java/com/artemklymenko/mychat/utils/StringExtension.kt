package com.artemklymenko.mychat.utils

import java.util.regex.Pattern

class StringExtension {

    fun checkEmail(email: String): Boolean {
        return emailAddressPattern.matcher(email).matches()
    }

    fun containsNumber(input: String): Boolean{
        val regex = Regex(".*\\d+.*")
        return regex.matches(input)
    }

    fun containsUpperCase(input: String): Boolean{
        val regex = Regex(".*[A-Z]+.*")
        return regex.matches(input)
    }

    fun containsSpecialChar(input: String): Boolean{
        val regex = Regex(".*[^A-Za-z\\d]+.*")
        return regex.matches(input)
    }

    private val emailAddressPattern: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}