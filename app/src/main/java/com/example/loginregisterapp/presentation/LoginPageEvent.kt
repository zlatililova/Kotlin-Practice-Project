package com.example.loginregisterapp.presentation

sealed class LoginPageEvent {
    data class EmailChanged(val email: String) : LoginPageEvent()
    data class PasswordChanged(val password: String) : LoginPageEvent()

    object Submit: LoginPageEvent()
}