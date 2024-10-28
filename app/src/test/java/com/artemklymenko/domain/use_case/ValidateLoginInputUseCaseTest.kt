package com.artemklymenko.domain.use_case

import com.artemklymenko.mychat.domain.model.LoginInputValidationType
import com.artemklymenko.mychat.domain.use_case.ValidateLoginInputUseCase
import org.junit.Test
import org.junit.Assert.assertEquals

class ValidateLoginInputUseCaseTest {

    private val validateLoginInputUseCase = ValidateLoginInputUseCase()

    @Test
    fun `test empty field return validation type empty field`(){
        val result = validateLoginInputUseCase.invoke("", "")
        assertEquals(LoginInputValidationType.EmptyField, result)
    }

    @Test
    fun `test invalid email returns validation type no email`() {
        val result = validateLoginInputUseCase.invoke("invalidEmail", "password123")
        assertEquals(LoginInputValidationType.NoEmail, result)
    }

    @Test
    fun `test valid input returns validation type valid`() {
        val result = validateLoginInputUseCase.invoke("test@example.com", "password123")
        assertEquals(LoginInputValidationType.Valid, result)
    }
}