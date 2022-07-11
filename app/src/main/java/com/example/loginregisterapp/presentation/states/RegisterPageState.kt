package com.example.loginregisterapp.presentation.states

data class RegisterPageState(
    val fname: String = "",
    val fnameError: String = "",
    val lname: String = "",
    val lnameError: String = "",
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val confpass: String = "",
    val confpassError: String = "",

)
