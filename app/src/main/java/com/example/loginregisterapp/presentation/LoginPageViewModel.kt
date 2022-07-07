package com.example.loginregisterapp.presentation

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.loginregisterapp.domain.use_case.ValidationEmail
import com.example.loginregisterapp.domain.use_case.ValidationPassword
import com.example.loginregisterapp.presentation.LoginPageEvent.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LoginPageViewModel(
    val validationEmail: ValidationEmail = ValidationEmail(),
    val validationPassword: ValidationPassword = ValidationPassword()
): ViewModel() {

    var state by mutableStateOf(LoginPageState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginPageEvent){
        when(event){
            is EmailChanged -> state = state.copy(email = event.email)
            is PasswordChanged -> state = state.copy(password = event.password)
            is Submit -> submitData()
        }
    }

    private fun submitData() {
        val email = validationEmail.execute(state.email)
        val pass = validationPassword.execute(state.password)

        if(!pass.successful || !email.successful){
            state = state.copy(
                emailError = email.errorMessage,
                passwordError = pass.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}
