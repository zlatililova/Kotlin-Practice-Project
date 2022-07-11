package com.example.loginregisterapp.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginregisterapp.domain.use_case.ValidationEmail
import com.example.loginregisterapp.domain.use_case.ValidationPassword
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.ArrayList

class LoginPageViewModel(
    val validationEmail: ValidationEmail = ValidationEmail(),
    val validationPassword: ValidationPassword = ValidationPassword()
): ViewModel() {

    private var email: String = ""
    private var pass : String = ""

    fun setEmail(email: String){
        this.email = email
    }

    fun setPass(pass: String){
        this.pass = pass
    }

    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)
    val uiState : StateFlow<UIState> = _uiState

    sealed class UIState {
        object Initial: UIState()
        object Success: UIState()
        object Loading: UIState()
        class Error(val errors: ArrayList<Errors>): UIState()

    }

    sealed class Errors{
        class EmailError(val error: String): Errors()
        class PassError(val error: String): Errors()
    }

    fun login(){
        viewModelScope.launch {
            val validEmail = validationEmail.execute(email)
            val validPass = validationPassword.execute(pass)

            if(validEmail.successful && validPass.successful){
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
                _uiState.emit(UIState.Error(errors))

            }


        }


    }

    fun checkValues(): Boolean{
        if(email != "" && pass != ""){
            return true
        }
        return false
    }
}
