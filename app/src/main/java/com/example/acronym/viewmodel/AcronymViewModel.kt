package com.example.acronym.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronym.dataSource.*

class AcronymViewModel(private val acronymListDataSource: AcronymListDataSource) : ViewModel() {
    var acronyms = MutableLiveData<AcronymResponse>()

    fun getAcronyms(enteredText: String) {
            acronymListDataSource.getAcronyms(enteredText, object : ResponseCallback {
                override fun onSuccess(acronymData: AcronymData?) {
                    if (acronymData != null) {
                        acronyms.postValue(Success(acronymData))
                    } else {
                        acronyms.postValue(Failure("response is null", NullPointerException()))
                    }
                }

                override fun onError(t: Throwable) {
                    // can handle errors in a better way if time not in time crunch
                    acronyms.postValue(Failure(t.message ?: "Error", t))
                }
            })
    }
}