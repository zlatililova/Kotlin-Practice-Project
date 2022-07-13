package com.example.loginregisterapp.data.authentication

import android.view.View

interface AuthDataInt {
    fun login(email: String, pass: String,  onLogin: OnLogin)
    fun register(email: String, pass: String, fname: String, lname: String, confpass: String, onRegister: OnRegister)

    interface OnLogin{
        fun onSuccess()
        fun onError(string: String?)
    }

    interface OnRegister{
        fun onSuccess()
        fun onError(string: String?)
    }
}