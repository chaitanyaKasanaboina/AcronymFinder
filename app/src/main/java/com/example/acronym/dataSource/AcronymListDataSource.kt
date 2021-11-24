package com.example.acronym.dataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AcronymListDataSource {
    private val retrieveAcronymServiceInfo: RetrieveAcronymServiceInfo

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.nactem.ac.uk/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrieveAcronymServiceInfo = retrofit.create(RetrieveAcronymServiceInfo::class.java)
    }

    fun getAcronyms(enteredString: String, responseCallback: ResponseCallback) {

        retrieveAcronymServiceInfo.getAcronymList(enteredString).enqueue(object :
            Callback<AcronymData> {
            override fun onResponse(call: Call<AcronymData>, response: Response<AcronymData>) {
                responseCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<AcronymData>, t: Throwable) {
                responseCallback.onError(t)
            }
        })
    }

}