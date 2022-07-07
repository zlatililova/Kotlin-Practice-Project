package com.example.loginregisterapp.domain.use_case

class ValidationConfirmPassword {

    fun execute(password: String, confirmPassword: String): ValidationResult{
        if(password != confirmPassword){
            return ValidationResult(false, "The passwords are not matching")
        }
        return ValidationResult(true)
    }
}