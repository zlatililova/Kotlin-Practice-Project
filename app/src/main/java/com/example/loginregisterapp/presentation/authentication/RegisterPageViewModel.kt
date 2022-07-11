package com.example.loginregisterapp.presentation.authentication

import androidx.lifecycle.ViewModel
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

    private val _uiStateFlow = MutableStateFlow<UIStateRegister>(UIStateRegister.Initial)
    val uiStateFlow : StateFlow<UIStateRegister> = _uiStateFlow

    sealed class UIStateRegister {
        object Initial: UIStateRegister()
        object Success: UIStateRegister()
        object Loading: UIStateRegister()
        class Error(val errors: ArrayList<RegisterErrors>): UIStateRegister()

    }

    sealed class RegisterErrors{
        class EmailError(val error: String): RegisterErrors()
        class PassError(val error: String): RegisterErrors()
        class FNameError(val error: String): RegisterErrors()
        class LNameError(val error: String): RegisterErrors()
        class ConfPassError(val error: String): RegisterErrors()
    }

    fun register(){
        viewModelScope.launch {
            val validEmail = validationEmail.execute(email)
            val validPass = validationPassword.execute(pass)
            val validFName = validationName.execute(fname)
            val validLName = validationName.execute(lname)
            val validConfPass = validationConfirmPassword.execute(pass, confPass)

            if(validEmail.successful && validPass.successful && validFName.successful && validConfPass.successful && validLName.successful){
                _uiStateFlow.emit(UIStateRegister.Success)
            }
            else{
                val errors : ArrayList<RegisterErrors> = ArrayList()
                validEmail.errors?.let {
                    errors.add(RegisterErrors.EmailError(validEmail.errors))
                }
                validPass.errors?.let {
                    errors.add(RegisterErrors.PassError(validPass.errors))
                }
                validFName.errors?.let {
                    errors.add(RegisterErrors.FNameError(validFName.errors))
                }
                validLName.errors?.let {
                    errors.add(RegisterErrors.LNameError(validLName.errors))
                }
                validConfPass.errors?.let {
                    errors.add(RegisterErrors.ConfPassError(validConfPass.errors))
                }
                _uiStateFlow.emit(UIStateRegister.Error(errors))

            }


        }


    }

    fun checkValues(): Boolean{
        if(email != "" && pass != "" && fname != "" && lname != "" && confPass != ""){
            return true
        }
        return false
    }
}