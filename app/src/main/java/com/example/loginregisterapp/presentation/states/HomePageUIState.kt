package com.example.loginregisterapp.presentation.states

import com.example.loginregisterapp.data.lastActivityDisplay.LastRunData

sealed class HomePageUIState {
    object Initial : HomePageUIState()
    data class Success(val runs: ArrayList<LastRunData>) : HomePageUIState()
    data class Error(val error: String?): HomePageUIState()
}