package com.example.loginregisterapp.domain.use_case

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.loginregisterapp.R
import com.example.loginregisterapp.data.authentication.AuthDataInt

class LoginUseCase(val authDataInt: AuthDataInt) {
    fun execute(email: String, pass: String,  onLogin: AuthDataInt.OnLogin
    ) {
        /*authDataInt.login(email, pass, object  : OnLogin {
            override fun onSuccess() {

            }

            o
        })*/

        authDataInt.login(email, pass, onLogin)

    }
}