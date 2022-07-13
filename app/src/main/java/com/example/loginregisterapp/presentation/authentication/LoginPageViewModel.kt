package com.example.loginregisterapp.presentation.authentication



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginregisterapp.data.authentication.AuthDataInt
import com.example.loginregisterapp.domain.use_case.LoginUseCase
import com.example.loginregisterapp.domain.use_case.ValidationEmail
import com.example.loginregisterapp.domain.use_case.ValidationPassword
import com.example.loginregisterapp.presentation.states.LoginUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginPageViewModel(
    val validationEmail: ValidationEmail,
    val validationPassword: ValidationPassword,
    val loginUseCase: LoginUseCase,
) : ViewModel() {

    private var email: String = ""
    private var pass: String = ""

    fun setEmail(email: String) {
        this.email = email
    }

    fun setPass(pass: String) {
        this.pass = pass
    }

    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Initial)
    val uiState: StateFlow<LoginUIState> = _uiState


    fun login() {
        viewModelScope.launch {

            _uiState.emit(LoginUIState.Loading)
            println("Loading emited")
        }

        loginUseCase.execute(email = email, pass = pass, object : AuthDataInt.OnLogin {
            override fun onSuccess() {
                viewModelScope.launch {
                    _uiState.emit(LoginUIState.Success)
                }
            }

            override fun onError(string: String?) {
                viewModelScope.launch {
                    _uiState.emit(LoginUIState.Error(string))
                }
            }

        })
    }




    fun checkValues(): Boolean {
        if (checkEmail() == "" && checkPass() == "") {
            return true
        }
        return false
    }

    fun checkEmail(): String {
        val validEmail = validationEmail.execute(email)
        return validEmail.errors
    }

    fun checkPass(): String {
        val validPass = validationPassword.execute(pass)
        return validPass.errors
    }




}
