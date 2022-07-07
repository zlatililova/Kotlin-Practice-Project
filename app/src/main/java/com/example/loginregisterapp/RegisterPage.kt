package com.example.loginregisterapp

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText


class RegisterPage : Fragment() {

    lateinit var button: Button
    lateinit var contain: View
    var validEmail = false
    var validPass = false
    var samepass = false
    var validfname = false
    var validlname = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contain = getView()!!
        val fname = contain.findViewById<TextInputEditText>(R.id.first_name)
        val lname = contain.findViewById<TextInputEditText>(R.id.last_name)
        val email = contain.findViewById<TextInputEditText>(R.id.EmailAddress)
        val pass = contain.findViewById<TextInputEditText>(R.id.Password)
        var confpass = contain.findViewById<TextInputEditText>(R.id.ConfirmPassword)
        var password : String = ""
        button = contain.findViewById(R.id.button_submit_register)

        fname.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s)) {
                    println("fname is true")
                    validfname = true
                    Activate()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        lname.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s)) {
                    println("lname is true")
                    validlname = true
                    Activate()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        email.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s) && android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    println("email is true")
                    validEmail = true
                    Activate()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        pass.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s)){
                    if(s.length > 6){
                        println("pass is true")
                        password = s.toString()
                        validPass = true
                        Activate()
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        confpass.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (!TextUtils.isEmpty(s)){
                    if(s.toString().equals(password)){
                        println("pass is same")
                        samepass = true
                        Activate()
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        button.setOnClickListener {
            
        }

        val loginTransition = contain.findViewById<Button>(R.id.button_to_register)

        loginTransition.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(contain)
            navGraphNavigator.navigate(R.id.action_registerPage_to_loginPage2)
        }

        val backStackButton = contain.findViewById<AppCompatImageView>(R.id.back_stack_btn)
        backStackButton.setOnClickListener {
            val navGraphNavigator = Navigation.findNavController(contain)
            navGraphNavigator.popBackStack()
        }

    }

    private fun Activate() {
        if (validPass && validEmail && validlname && validfname && samepass){
            println("Button can be enabled")
            button.isEnabled = true
            println(button.isEnabled)
        }
    }
}