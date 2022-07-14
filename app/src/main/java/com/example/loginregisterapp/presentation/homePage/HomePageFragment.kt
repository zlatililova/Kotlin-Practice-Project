package com.example.loginregisterapp.presentation.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginregisterapp.R
import com.example.loginregisterapp.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : BaseFragment() {

    val viewModel: HomePageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.home_page_lastrun_rv)
        recyclerView.adapter = viewModel.homePageAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        if(!viewModel.successQuery){
            Toast.makeText(
                context,
                "Error: " + viewModel.errors ,
                Toast.LENGTH_LONG
            ).show()
        }



    }

}