package com.artemklymenko.mychat.di

import com.artemklymenko.mychat.domain.use_case.ValidateLoginInputUseCase
import com.artemklymenko.mychat.domain.use_case.ValidateRegisterInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(): ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCase()
    }
}