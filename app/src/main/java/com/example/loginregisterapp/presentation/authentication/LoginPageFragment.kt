package com.example.loginregisterapp.presentation.authentication

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.loginregisterapp.R
import com.example.loginregisterapp.presentation.BaseFragment
import com.example.loginregisterapp.presentation.states.LoginUIState
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginPage : BaseFragment() {

    private lateinit var loginButton: Button
    private lateinit var registerTransition: Button
    val viewModel: LoginPageViewModel by viewModel()
    lateinit var emailLayout: TextInputLayout
    lateinit var passwordLayout: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton = view.findViewById(R.id.button_submit_login)
        registerTransition = view.findViewById(R.id.button_to_login)
        emailLayout = view.findViewById(R.id.TextInputLayoutEmailAddress)
        passwordLayout = view.findViewById(R.id.TextInputLayoutPassword)
        val email = view.findViewById<TextInputEditText>(R.id.EmailAddress)
        val password = view.findViewById<TextInputEditText>(R.id.Password)



        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setEmail(s.toString())
                emailLayout.error = viewModel.checkEmail()
                changeButton(loginButton, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setPass(s.toString())
                passwordLayout.error = viewModel.checkPass()
                changeButton(loginButton, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        loginButton.setOnClickListener {
            showProgress()
            //delay
            Handler().postDelayed({
                viewModel.login()
            }, 1000)

            //crashlytics test - leave it as comments
            //throw RuntimeException("Test Crash")

        }

        /*recycler review*/

        registerTransition.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(view)
            navGraphNavigator.navigate(R.id.action_loginPage_to_registerPage2)
        }

        observeViewModel(view)
    }


    private fun observeViewModel(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach {
                    when (it) {
                        is LoginUIState.Success -> {
                            Toast.makeText(
                                context,
                                "Success",
                                Toast.LENGTH_LONG
                            ).show()
                            hideProgress()
                            val navGraphNavigator = Navigation.findNavController(view)
                            navGraphNavigator.navigate(R.id.action_loginPage_to_homePageFragment)
                        }
                        is LoginUIState.Error -> {
                            /*Toast.makeText(
                                context,
                                "Error: " + it.error,
                                Toast.LENGTH_LONG
                            ).show()*/
                            hideProgress()
                        }
                        is LoginUIState.Loading -> {
                            println("Loading state")
                            loginButton.isEnabled = false
                            registerTransition.isEnabled = false
                        }
                        else->{}
                    }
                }.launchIn(this)
            }

        }
    }

    private fun changeButton(button: Button, state: Boolean) {
        button.isEnabled = state
    }

}




