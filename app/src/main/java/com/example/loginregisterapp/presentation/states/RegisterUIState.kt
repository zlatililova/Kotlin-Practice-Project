package com.example.loginregisterapp.presentation.states

import com.example.loginregisterapp.presentation.authentication.RegisterPageViewModel
import java.util.ArrayList

sealed class RegisterUIState {
    object Initial: RegisterUIState()
    object Success: RegisterUIState()
    object Loading: RegisterUIState()
    class Error(val error: String?): RegisterUIState()

}