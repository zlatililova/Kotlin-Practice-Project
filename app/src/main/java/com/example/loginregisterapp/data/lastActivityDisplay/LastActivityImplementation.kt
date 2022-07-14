package com.example.loginregisterapp.data.lastActivityDisplay

class LastActivityImplementation: LastActivityInterface {
    override fun lastTenRuns(onQuery: LastActivityInterface.onQuery) {
        var lastruns: ArrayList<LastRunData> = ArrayList()
        lastruns.add(LastRunData("Saturday run", 5.0f))
        lastruns.add(LastRunData("Sunday run", 3.0f))
        lastruns.add(LastRunData("Monday run", 6.0f))
        lastruns.add(LastRunData("Tuesday run", 7.0f))
        lastruns.add(LastRunData("Wednesday run", 5.5f))
        lastruns.add(LastRunData("Thursday run", 5.7f))
        lastruns.add(LastRunData("Friday run", 4.9f))
        lastruns.add(LastRunData("Saturday run 2", 5.1f))
        lastruns.add(LastRunData("Sunday run 2", 4.0f))
        lastruns.add(LastRunData("Monday run 2", 4.5f))
        lastruns.add(LastRunData("Tuesday run 2", 5.2f))
        lastruns.add(LastRunData("Wednesday run 2", 5.4f))
        lastruns.add(LastRunData("Thursady run 2", 5.7f))

        //onQuery.onSuccess(lastruns)
        onQuery.onError("Cannot render the last runs")

    }
}