package com.example.loginregisterapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.loginregisterapp.R
import com.google.android.material.textfield.TextInputEditText


class LoginPage : Fragment(){

    lateinit var button: Button
    lateinit var registerTransition: Button
    lateinit var contain: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_login_page,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contain = getView()!!
        val email = contain.findViewById<TextInputEditText>(R.id.EmailAddress)
        val pass = contain.findViewById<TextInputEditText>(R.id.Password)
        button = contain.findViewById(R.id.button_submit_login)
        registerTransition = contain.findViewById(R.id.button_to_login)
        var validEmail = false
        var validPass = false

        email.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s) && android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    println("email is true")
                    validEmail = true
                    Activate(validPass, validEmail)
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        pass.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s)){
                    if(s.length > 6){
                        println("pass is true")
                        validPass = true
                        Activate(validPass, validEmail)
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        button.setOnClickListener {
        }


        registerTransition.setOnClickListener {
            val navGraphNavigator = findNavController(contain)
            navGraphNavigator.navigate(R.id.action_loginPage_to_registerPage2)
        }

    }

    private fun Activate(pass: Boolean, email: Boolean) {
        if (pass && email){
            println("Button can be enabled")
            button.isEnabled = true
            println(button.isEnabled)
        }
    }

}
