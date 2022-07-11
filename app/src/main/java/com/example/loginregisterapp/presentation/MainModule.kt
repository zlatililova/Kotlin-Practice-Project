package com.example.loginregisterapp.presentation

import com.example.loginregisterapp.domain.use_case.ValidationConfirmPassword
import com.example.loginregisterapp.domain.use_case.ValidationEmail
import com.example.loginregisterapp.domain.use_case.ValidationName
import com.example.loginregisterapp.domain.use_case.ValidationPassword
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module{
        factory { ValidationEmail() }
        factory { ValidationPassword() }
        factory { ValidationName() }
        factory { ValidationConfirmPassword() }
        factory { LoginPageViewModel(get()) }
        factory { RegisterPageViewModel(validationPassword = get(), validationEmail = get(), validationConfirmPassword = get(), validationName = get()) }
        /*factory { LoginViewModel(get()) }
        factory { ProfileViewModel(get()) }*/

}