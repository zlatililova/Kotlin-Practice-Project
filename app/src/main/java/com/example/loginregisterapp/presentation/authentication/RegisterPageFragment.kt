package com.example.loginregisterapp.presentation.authentication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import androidx.navigation.Navigation
import com.example.loginregisterapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel



class RegisterPageFragment : Fragment() {

    lateinit var button: Button
    lateinit var emailLayout: TextInputLayout
    lateinit var passwordLayout: TextInputLayout
    lateinit var confpassLayout: TextInputLayout
    lateinit var fnameLayout: TextInputLayout
    lateinit var lnameLayout: TextInputLayout
    val viewModel: RegisterPageViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fname = view.findViewById<TextInputEditText>(R.id.Register_first_name)
        val lname = view.findViewById<TextInputEditText>(R.id.Register_last_name)
        val email = view.findViewById<TextInputEditText>(R.id.Register_EmailAddress)
        val pass = view.findViewById<TextInputEditText>(R.id.Register_Password)
        var confpass = view.findViewById<TextInputEditText>(R.id.Register_ConfirmPassword)
        button = view.findViewById(R.id.button_submit_register)
        emailLayout = view.findViewById(R.id.Register_TextInputLayoutEmailAddress)
        passwordLayout = view.findViewById(R.id.Register_TextInputLayoutPassword)
        fnameLayout = view.findViewById(R.id.Register_TextInputLayoutFirstName)
        lnameLayout = view.findViewById(R.id.Register_TextInputLayoutLastName)
        confpassLayout = view.findViewById(R.id.Register_TextInputLayoutConfirmPassword)



        fname.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                viewModel.setfname(s.toString())
                changebutton(button, viewModel.checkValues())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        lname.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                viewModel.setlname(s.toString())
                changebutton(button, viewModel.checkValues())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        email.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                viewModel.setemail(s.toString())
                changebutton(button, viewModel.checkValues())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        pass.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                viewModel.setpass(s.toString())
                changebutton(button, viewModel.checkValues())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        confpass.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                viewModel.setConfPass(s.toString())
                changebutton(button, viewModel.checkValues())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        button.setOnClickListener {
            viewModel.register()
        }

        observeViewModel()

        val loginTransition = view.findViewById<Button>(R.id.button_to_register)

        loginTransition.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(view)
            navGraphNavigator.navigate(R.id.action_registerPage_to_loginPage2)
        }

        val backStackButton = view.findViewById<AppCompatImageView>(R.id.back_stack_btn)
        backStackButton.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(view)
            navGraphNavigator.popBackStack()
        }

    }

    private fun observeViewModel(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.onEach {
                    when (it) {
                        is RegisterPageViewModel.UIStateRegister.Success -> {
                            emailLayout.setError(null)
                            passwordLayout.setError(null)
                            fnameLayout.setError(null)
                            lnameLayout.setError(null)
                            confpassLayout.setError(null)
                            Toast.makeText(
                                context,
                                "Registration successful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is RegisterPageViewModel.UIStateRegister.Error -> {
                            it.errors.forEach{
                                when (it) {
                                    is RegisterPageViewModel.RegisterErrors.EmailError -> {
                                        if(it.error == ""){
                                            emailLayout.setError(null)
                                        }
                                        emailLayout.setError(it.error)
                                    }
                                    is RegisterPageViewModel.RegisterErrors.PassError -> {
                                        if(it.error == ""){
                                            passwordLayout.setError(null)
                                        }
                                        passwordLayout.setError(it.error)
                                    }
                                    is RegisterPageViewModel.RegisterErrors.FNameError -> {
                                        if(it.error == ""){
                                            fnameLayout.setError(null)
                                        }
                                        fnameLayout.setError(it.error)
                                    }
                                    is RegisterPageViewModel.RegisterErrors.LNameError -> {
                                        if(it.error == ""){
                                            lnameLayout.setError(null)
                                        }
                                        lnameLayout.setError(it.error)
                                    }
                                    is RegisterPageViewModel.RegisterErrors.ConfPassError -> {
                                        if(it.error == ""){
                                            confpassLayout.setError(null)
                                        }
                                        confpassLayout.setError(it.error)
                                    }
                                }
                            }
                        }
                        is RegisterPageViewModel.UIStateRegister.Loading -> {
                            emailLayout.setError(null)
                            passwordLayout.setError(null)
                            fnameLayout.setError(null)
                            lnameLayout.setError(null)
                            confpassLayout.setError(null)

                        }
                        else -> {}
                    }
                }.launchIn(this)
            }

        }
    }

    private fun changebutton(button: Button, state: Boolean){
        button.isEnabled = state
    }


}