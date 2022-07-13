package com.example.loginregisterapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    var activityaction: ActivityActions? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mainActivity = requireActivity() as MainActivity
        if(activity is ActivityActions){
            activityaction = activity as ActivityActions
        }
        println("Activity: " + activityaction.toString())
    }
    protected fun showProgress(){
        if(activityaction != null) {
            activityaction?.showProgress()
        }
    }
    protected fun hideProgress(){
        if(activityaction != null) {
            activityaction?.hideProgress()
        }
    }
}