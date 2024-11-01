package com.artemklymenko.domain.use_case

import com.artemklymenko.mychat.domain.model.RegisterInputValidationType
import com.artemklymenko.mychat.domain.use_case.ValidateRegisterInputUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidateRegisterInputUseCaseTest {

    private val validateRegisterUseCase = ValidateRegisterInputUseCase()

    @Test
    fun `test empty email field return validation type empty field`() {
        val result = validateRegisterUseCase.invoke(
            email = "",
            password = "Password+1",
            passwordRepeated = "Password+1"
        )
        assertEquals(RegisterInputValidationType.EmptyField, result)
    }

    @Test
    fun `test empty password field return validation type empty field`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "",
            passwordRepeated = "Password+1"
        )
        assertEquals(RegisterInputValidationType.EmptyField, result)
    }

    @Test
    fun `test empty repeated password field return validation type empty field`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "Password+1",
            passwordRepeated = ""
        )
        assertEquals(RegisterInputValidationType.EmptyField, result)
    }

    @Test
    fun `test email without '@' return validation type no email`() {
        val result = validateRegisterUseCase.invoke(
            email = "user12test.com",
            password = "Password+1",
            passwordRepeated = "Password+1",
        )
        assertEquals(RegisterInputValidationType.NoEmail, result)
    }

    @Test
    fun `test email without dot return validation type no email`() {
        val result = validateRegisterUseCase.invoke(
            email = "user12@testcom",
            password = "Password+1",
            passwordRepeated = "Password+1"
        )
        assertEquals(RegisterInputValidationType.NoEmail, result)
    }

    @Test
    fun `test email with a first character as a number return validation type no email`() {
        val result = validateRegisterUseCase.invoke(
            email = "1user@testcom",
            password = "Password+1",
            passwordRepeated = "Password+1"
        )
        assertEquals(RegisterInputValidationType.NoEmail, result)
    }

    @Test
    fun `test length of the email`() {
        val result = validateRegisterUseCase.invoke(
            email = "u@t.c",
            password = "Password+1",
            passwordRepeated = "Password+1"
        )
        assertEquals(RegisterInputValidationType.EmailIsTooShort, result)
    }

    @Test
    fun `test length of the password`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "Pass+1",
            passwordRepeated = "Pass+1"
        )
        assertEquals(RegisterInputValidationType.PasswordIsTooShort, result)
    }

    @Test
    fun `test string contains no character returns false when check for it`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "Password1",
            passwordRepeated = "Password1"
        )
        assertEquals(RegisterInputValidationType.PasswordSpecialCharMissing, result)
    }

    @Test
    fun `test string contains no number returns false when check for it`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "Password+",
            passwordRepeated = "Password+"
        )
        assertEquals(RegisterInputValidationType.PasswordNumberMissing, result)
    }

    @Test
    fun `test string contains no upper case return false when check for it`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "password+1",
            passwordRepeated = "password+1"
        )
        assertEquals(RegisterInputValidationType.PasswordUpperCaseMissing, result)
    }

    @Test
    fun `test if passwords matches`() {
        val result = validateRegisterUseCase.invoke(
            email = "user1@test.com",
            password = "Password+1",
            passwordRepeated = "Passw0rd+2"
        )
        assertEquals(RegisterInputValidationType.PasswordsDoNotMatch, result)
    }
}