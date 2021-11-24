package com.example.acronym.dataSource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrieveAcronymServiceInfo {

    @GET("/software/acromine/dictionary.py?")
    fun getAcronymList(@Query("sf") sf: String): Call<AcronymData>
}