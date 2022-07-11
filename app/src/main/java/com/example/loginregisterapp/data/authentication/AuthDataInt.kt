package com.example.loginregisterapp.data.authentication

import android.app.Activity

interface AuthDataInt {
    fun login(email: String, pass: String, onLogin: OnLogin)
    fun register(email: String, pass: String, fname: String, lname: String, confpass: String)

    interface OnLogin{
        fun onSuccess()
        fun onError()
    }

    interface OnRegister{
        fun onSuccess()
    }
}