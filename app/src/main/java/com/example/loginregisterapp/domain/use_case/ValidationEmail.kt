package com.example.loginregisterapp.domain.use_case

import android.text.TextUtils

class ValidationEmail {

    fun execute(email: String): ValidationResult{
        if (TextUtils.isEmpty(email)){
            return ValidationResult(false, "The email cannot be blank")
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(false, "The email is not valid")
        }
        return ValidationResult(true)
    }
}