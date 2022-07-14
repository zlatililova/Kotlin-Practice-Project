package com.example.loginregisterapp.data.lastActivityDisplay

import java.util.ArrayList

interface LastActivityInterface {
    fun lastTenRuns(onQuery: onQuery)

    interface onQuery{
        fun onSuccess(lastTenRuns: ArrayList<LastRunData>)
        fun onError(error: String?)
    }
}