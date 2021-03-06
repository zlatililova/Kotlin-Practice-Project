package com.example.loginregisterapp.presentation

import com.example.loginregisterapp.data.authentication.AuthDataImplenentation
import com.example.loginregisterapp.data.authentication.AuthDataInt
import com.example.loginregisterapp.data.lastActivityDisplay.LastActivityImplementation
import com.example.loginregisterapp.data.lastActivityDisplay.LastActivityInterface
import com.example.loginregisterapp.domain.use_case.*
import com.example.loginregisterapp.presentation.authentication.LoginPageViewModel
import com.example.loginregisterapp.presentation.authentication.RegisterPageViewModel
import com.example.loginregisterapp.presentation.homePage.HomePageViewModel
import org.koin.dsl.module

val appModule = module{
        factory<AuthDataInt> { AuthDataImplenentation() }
        factory<LastActivityInterface> { LastActivityImplementation() }
        factory { ValidationEmail() }
        factory { ValidationPassword() }
        factory { ValidationName() }
        factory { ValidationConfirmPassword() }
        factory { LoginUseCase(authDataInt = get()) }
        factory { RegisterUseCase(authDataInt = get()) }
        factory { LastTenRunsUseCase(lastActivityInterface = get()) }
        factory { LoginPageViewModel(validationEmail = get(), validationPassword = get(), loginUseCase = get()) }
        factory { RegisterPageViewModel(validationPassword = get(), validationEmail = get(), validationConfirmPassword = get(), validationName = get(), registerUseCase = get() )}
        factory { HomePageViewModel(lastTenRunsUseCase = get()) }

        /*factory { LoginViewModel(get()) }
        factory { ProfileViewModel(get()) }*/

}