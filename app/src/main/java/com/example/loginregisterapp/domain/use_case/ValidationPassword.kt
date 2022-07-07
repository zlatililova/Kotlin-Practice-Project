package com.example.loginregisterapp.domain.use_case

import android.text.TextUtils

class ValidationPassword {

    fun execute(password: String): ValidationResult{
        if(password.length <= 6){
             return ValidationResult(false, "The password should be longer than 6 characters")
        }
        return ValidationResult(true)
    }

}