package com.example.loginregisterapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.loginregisterapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginPage : Fragment() {

    lateinit var loginButton: Button
    lateinit var registerTransition: Button
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
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setPass(s.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        loginButton.setOnClickListener {
            viewModel.login()
        }

        registerTransition.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(view)
            navGraphNavigator.navigate(R.id.action_loginPage_to_registerPage2)
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.onEach {
                    when (it) {
                        is LoginPageViewModel.UIState.Success -> {
                            emailLayout.setError(null)
                            passwordLayout.setError(null)
                            Toast.makeText(
                                context,
                                "Login successful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is LoginPageViewModel.UIState.Error -> {
                            it.errors.forEach{
                                when (it) {
                                    is LoginPageViewModel.Errors.EmailError -> {
                                        if(it.error == ""){
                                            emailLayout.setError(null)
                                        }
                                        emailLayout.setError(it.error)
                                    }
                                    is LoginPageViewModel.Errors.PassError -> {
                                        if(it.error == ""){
                                            passwordLayout.setError(null)
                                        }
                                        passwordLayout.setError(it.error)
                                    }
                                }
                            }
                        }
                        is LoginPageViewModel.UIState.Loading -> {
                            emailLayout.setError(null)
                            passwordLayout.setError(null)

                        }
                        else -> {

                        }
                    }
                }.launchIn(this)
            }

        }
    }
}




