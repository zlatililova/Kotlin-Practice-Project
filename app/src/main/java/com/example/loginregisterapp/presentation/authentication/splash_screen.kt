package com.example.loginregisterapp.presentation.authentication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginregisterapp.R

class splash_screen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val navGraphNavigator = Navigation.findNavController(view)
            navGraphNavigator.navigate(R.id.action_splash_screen_to_loginPage)
        }, 1000)

    }*/
}