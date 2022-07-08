package com.example.loginregisterapp.presentation

import androidx.lifecycle.viewModelScope
import com.example.loginregisterapp.domain.use_case.ValidationConfirmPassword
import com.example.loginregisterapp.domain.use_case.ValidationEmail
import com.example.loginregisterapp.domain.use_case.ValidationName
import com.example.loginregisterapp.domain.use_case.ValidationPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.ArrayList

class RegisterPageViewModel(
    val validationEmail: ValidationEmail = ValidationEmail(),
    val validationPassword: ValidationPassword = ValidationPassword(),
    val validationName: ValidationName = ValidationName(),
    val validationConfirmPassword: ValidationConfirmPassword = ValidationConfirmPassword()
) {
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

    private val _uiState = MutableStateFlow<LoginPageViewModel.UIState>(LoginPageViewModel.UIState.Initial)
    val uiState : StateFlow<LoginPageViewModel.UIState> = _uiState

    sealed class UIState {
        object Initial: UIState()
        object Success: UIState()
        object Loading: UIState()
        class Error(val errors: ArrayList<Errors>): UIState()

    }

    sealed class Errors{
        class EmailError(val error: String): Errors()
        class PassError(val error: String): Errors()
        class FNameError(val error: String): Errors()
        class LNameError(val error: String): Errors()
        class ConfPassError(val error: String): Errors()
    }

    fun register(){
        viewModelScope.launch {
            val validEmail = validationEmail.execute(email)
            val validPass = validationPassword.execute(pass)
            val validFName = validationName.execute(fname)
            val validLName = validationName.execute(lname)
            val validConfPass = validationConfirmPassword.execute(pass, confPass)

            if(validEmail.successful && validPass.successful && validFName.successful && validConfPass.successful && validLName.successful){
                _uiState.emit(UIState.Success)
                return@launch
            }
            else{
                val errors : ArrayList<Errors> = ArrayList()
                validEmail.errors?.let {
                    errors.add(Errors.EmailError(validEmail.errors))
                }
                validPass.errors?.let {
                    errors.add(Errors.PassError(validPass.errors))
                }
                validFName.errors?.let {
                    errors.add(Errors.FNameError(validFName.errors))
                }
                validLName.errors?.let {
                    errors.add(Errors.LNameError(validLName.errors))
                }
                validConfPass.errors?.let {
                    errors.add(Errors.ConfPassError(validConfPass.errors))
                }
                _uiState.emit(UIState.Error(errors))

            }


        }


    }
}