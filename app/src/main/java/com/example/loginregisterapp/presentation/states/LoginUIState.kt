package com.example.loginregisterapp.presentation.states

import com.example.loginregisterapp.presentation.authentication.LoginPageViewModel
import java.util.ArrayList

sealed class LoginUIState {
    object Initial: LoginUIState()
    object Success: LoginUIState()
    object Loading: LoginUIState()
    class Error(val error: String?): LoginUIState()

}