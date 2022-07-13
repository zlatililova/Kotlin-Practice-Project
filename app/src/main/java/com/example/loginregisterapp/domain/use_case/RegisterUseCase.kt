package com.example.loginregisterapp.domain.use_case

import com.example.loginregisterapp.data.authentication.AuthDataInt

class RegisterUseCase(val authDataInt: AuthDataInt) {

    fun execute(email: String, pass: String, fname: String, lname: String, confpass: String, onRegister: AuthDataInt.OnRegister){
        authDataInt.register(email, pass, fname, lname, confpass, onRegister)
    }
}