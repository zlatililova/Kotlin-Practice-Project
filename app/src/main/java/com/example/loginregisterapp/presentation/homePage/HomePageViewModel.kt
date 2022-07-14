package com.example.loginregisterapp.presentation.homePage

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.loginregisterapp.data.lastActivityDisplay.LastActivityInterface
import com.example.loginregisterapp.data.lastActivityDisplay.LastRunData
import com.example.loginregisterapp.domain.use_case.LastTenRunsUseCase
import java.util.ArrayList

class HomePageViewModel(
    val lastTenRunsUseCase: LastTenRunsUseCase
): ViewModel() {

    init {
        getLastRuns()
    }
    lateinit var homePageAdapter: HomePageAdapter
    var successQuery: Boolean = false
    lateinit var errors: String

    fun getLastRuns(){
        lastTenRunsUseCase.execute(object: LastActivityInterface.onQuery{
            override fun onSuccess(lastTenRuns: ArrayList<LastRunData>) {
                homePageAdapter = HomePageAdapter(lastTenRuns)
                successQuery = true
            }

            override fun onError(error: String?) {
                homePageAdapter = HomePageAdapter(ArrayList())
                errors = error!!
            }

        })
    }

}