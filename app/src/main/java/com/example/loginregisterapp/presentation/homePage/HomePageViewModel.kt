package com.example.loginregisterapp.presentation.homePage

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.loginregisterapp.R
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

    fun getProfilePic(view: View){
        val image = view.findViewById<ImageView>(R.id.home_page_profile_pic)
        Glide.with(view.context)
            .load("https://source.unsplash.com/random")
            .skipMemoryCache(true)
            .into(image)
    }

}