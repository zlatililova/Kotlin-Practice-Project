package com.example.loginregisterapp.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginregisterapp.data.authentication.AuthDataInt
import com.example.loginregisterapp.domain.use_case.ValidationConfirmPassword
import com.example.loginregisterapp.domain.use_case.ValidationEmail
import com.example.loginregisterapp.domain.use_case.ValidationName
import com.example.loginregisterapp.domain.use_case.ValidationPassword
import com.example.loginregisterapp.presentation.states.LoginUIState
import com.example.loginregisterapp.presentation.states.RegisterUIState
import com.example.loginregisterapp.presentation.states.UIStateRegister
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.ArrayList

class RegisterPageViewModel(
    val validationEmail: ValidationEmail,
    val validationPassword: ValidationPassword,
    val validationName: ValidationName,
    val validationConfirmPassword: ValidationConfirmPassword
): ViewModel() {
    private var fname: String = ""
    fun setfname(value: String) {
        fname = value
    }
    private var lname: String = ""
    fun setlname(value: String) {
        lname = value
    }
    private var email: String = ""
    fun setemail(value: String) {
        email = value
    }
    private var pass: String = ""
    fun setpass(value: String) {
        pass = value
        }
    private var confPass: String = ""
    fun setConfPass(value: String) {
        confPass = value
        }

    private val _uiStateFlow = MutableStateFlow<RegisterUIState>(RegisterUIState.Initial)
    val uiStateFlow : StateFlow<RegisterUIState> = _uiStateFlow


    fun register(){
        viewModelScope.launch {

            _uiStateFlow.emit(RegisterUIState.Loading)
            println("Loading emited")
        }

        loginUseCase.execute(email = email, pass = pass, object : AuthDataInt.OnLogin {
            override fun onSuccess() {
                viewModelScope.launch {
                    _uiStateFlow.emit(LoginUIState.Success)
                }
            }

            override fun onError(string: String?) {
                viewModelScope.launch {
                    _uiStateFlow.emit(LoginUIState.Error(string))
                }
            }

        })
    }

    fun checkValues(): Boolean{
        if(email != "" && pass != "" && fname != "" && lname != "" && confPass != ""){
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
    fun checkFName(): String {
        val checkName = validationName.execute(fname)
        return checkName.errors

    }
    fun checkLName(): String {
        val validLName = validationName.execute(lname)
        return validLName.errors

    }
    fun checkConfPass(): String {
        val validConfPass = validationConfirmPassword.execute(password = pass, confirmPassword = confPass)
        return validConfPass.errors

    }

}