package com.example.loginregisterapp.domain.use_case

import android.text.TextUtils

class ValidationName {

    fun execute(name: String): ValidationResult{
        if (TextUtils.isEmpty(name)) {
            return ValidationResult(false, "This field is obligatory")
        }
        return ValidationResult(true)
    }
}