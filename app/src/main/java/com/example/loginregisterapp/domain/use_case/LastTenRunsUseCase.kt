package com.example.loginregisterapp.domain.use_case

import com.example.loginregisterapp.data.lastActivityDisplay.LastActivityInterface

class LastTenRunsUseCase(val lastActivityInterface: LastActivityInterface) {
    fun execute(onQuery: LastActivityInterface.onQuery) {
        lastActivityInterface.lastTenRuns(onQuery)
    }
}