package com.example.loginregisterapp.presentation.authentication

import android.os.Bundle
import android.os.Handler
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
import com.example.loginregisterapp.presentation.BaseFragment
import com.example.loginregisterapp.presentation.states.RegisterUIState
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterPageFragment : BaseFragment() {

    lateinit var button: Button
    lateinit var emailLayout: TextInputLayout
    lateinit var passwordLayout: TextInputLayout
    lateinit var confpassLayout: TextInputLayout
    lateinit var fnameLayout: TextInputLayout
    lateinit var lnameLayout: TextInputLayout
    val viewModel: RegisterPageViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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
        val backStackButton = view.findViewById<AppCompatImageView>(R.id.back_stack_btn)
        emailLayout = view.findViewById(R.id.Register_TextInputLayoutEmailAddress)
        passwordLayout = view.findViewById(R.id.Register_TextInputLayoutPassword)
        fnameLayout = view.findViewById(R.id.Register_TextInputLayoutFirstName)
        lnameLayout = view.findViewById(R.id.Register_TextInputLayoutLastName)
        confpassLayout = view.findViewById(R.id.Register_TextInputLayoutConfirmPassword)



        fname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setfname(s.toString())
                fnameLayout.error = viewModel.checkFName()
                changebutton(button, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        lname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setlname(s.toString())
                lnameLayout.error = viewModel.checkLName()
                changebutton(button, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setemail(s.toString())
                emailLayout.error = viewModel.checkEmail()
                changebutton(button, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        pass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setpass(s.toString())
                passwordLayout.error = viewModel.checkPass()
                changebutton(button, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        confpass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setConfPass(s.toString())
                confpassLayout.error = viewModel.checkConfPass()
                changebutton(button, viewModel.checkValues())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        button.setOnClickListener {
            showProgress()
            //delay
            Handler().postDelayed({
                viewModel.register()
            }, 1000)
        }

        backStackButton.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(view)
            navGraphNavigator.popBackStack()
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiStateFlow.onEach {
                    when (it) {
                       is RegisterUIState
                    }
                }.launchIn(this)
            }

        }
    }

    private fun changebutton(button: Button, state: Boolean) {
        button.isEnabled = state
    }


}