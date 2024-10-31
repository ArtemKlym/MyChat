package com.artemklymenko.mychat.domain.use_case

import com.artemklymenko.mychat.domain.model.LoginInputValidationType
import com.artemklymenko.mychat.presentation.utils.StringExtension

class ValidateLoginInputUseCase {

    operator fun invoke(email: String, password: String) : LoginInputValidationType {
        if(email.isEmpty() || password.isEmpty()){
            return LoginInputValidationType.EmptyField
        }
        if(!StringExtension().checkEmail(email)){
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }
}