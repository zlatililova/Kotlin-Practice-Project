package com.example.loginregisterapp.data.lastActivityDisplay

class LastActivityImplementation: LastActivityInterface {
    override fun lastTenRuns(onQuery: LastActivityInterface.onQuery) {
        val lastruns: ArrayList<LastRunData> = ArrayList()
        lastruns.add(LastRunData("Saturday run", 5.0f, "http://clipart-library.com/images/8iGEnej5T.jpg"))
        lastruns.add(LastRunData("Sunday run", 3.0f, "https://cdn.xxl.thumbs.canstockphoto.com/running-dna-body-human-medical-molecule-science-man-genetic-health-anatomy-cell-evolution-clip-art-vector_csp11872862.jpg"))
        lastruns.add(LastRunData("Monday run", 6.0f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuuht5tCBh16TONvZSMQuSDzYgIrNSDnYlsOFMwT1D60vqdCAmHP2ePncCnsEjnR-CiAg&usqp=CAU"))
        lastruns.add(LastRunData("Tuesday run", 7.0f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3OTLOrlY1qAPvnUBullgJUj7RDJypaBQejHyj-3c6AHPiV4Ewgqq6YVk-N0BL12mCdFc&usqp=CAU"))
        lastruns.add(LastRunData("Wednesday run", 5.5f, "http://clipart-library.com/images/8iGEnej5T.jpg"))
        lastruns.add(LastRunData("Thursday run", 5.7f, "https://cdn.xxl.thumbs.canstockphoto.com/running-dna-body-human-medical-molecule-science-man-genetic-health-anatomy-cell-evolution-clip-art-vector_csp11872862.jpg"))
        lastruns.add(LastRunData("Friday run", 4.9f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuuht5tCBh16TONvZSMQuSDzYgIrNSDnYlsOFMwT1D60vqdCAmHP2ePncCnsEjnR-CiAg&usqp=CAU"))
        lastruns.add(LastRunData("Saturday run 2", 5.1f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3OTLOrlY1qAPvnUBullgJUj7RDJypaBQejHyj-3c6AHPiV4Ewgqq6YVk-N0BL12mCdFc&usqp=CAU"))
        lastruns.add(LastRunData("Sunday run 2", 4.0f, "http://clipart-library.com/images/8iGEnej5T.jpg"))
        lastruns.add(LastRunData("Monday run 2", 4.5f, "https://cdn.xxl.thumbs.canstockphoto.com/running-dna-body-human-medical-molecule-science-man-genetic-health-anatomy-cell-evolution-clip-art-vector_csp11872862.jpg"))
        lastruns.add(LastRunData("Tuesday run 2", 5.2f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuuht5tCBh16TONvZSMQuSDzYgIrNSDnYlsOFMwT1D60vqdCAmHP2ePncCnsEjnR-CiAg&usqp=CAU"))
        lastruns.add(LastRunData("Wednesday run 2", 5.4f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3OTLOrlY1qAPvnUBullgJUj7RDJypaBQejHyj-3c6AHPiV4Ewgqq6YVk-N0BL12mCdFc&usqp=CAU"))
        lastruns.add(LastRunData("Thursady run 2", 5.7f, "http://clipart-library.com/images/8iGEnej5T.jpg"))

        onQuery.onSuccess(lastruns)
        //onQuery.onError("Cannot render the last runs")

    }
}