package com.example.loginregisterapp.presentation.states

data class LoginPageState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)
