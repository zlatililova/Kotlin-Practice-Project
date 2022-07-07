package com.example.loginregisterapp.presentation

data class LoginPageState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)
