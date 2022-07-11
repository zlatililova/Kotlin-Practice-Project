package com.example.loginregisterapp.data.authentication

import android.app.Activity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AuthDataImplenentation: AuthDataInt {
    override fun login(
        email: String,
        pass: String, onLogin: AuthDataInt.OnLogin
    ) {

        runBlocking { launch {
            delay(1000)
            onLogin.onError()
        } }

    }

    override fun register(
        email: String,
        pass: String,
        fname: String,
        lname: String,
        confpass: String,

    ) {
    }

}