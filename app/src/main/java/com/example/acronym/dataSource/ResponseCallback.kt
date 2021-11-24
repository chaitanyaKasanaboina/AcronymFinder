package com.example.acronym.dataSource

interface ResponseCallback {
    fun onSuccess(acronymData: AcronymData?)
    fun onError(t: Throwable)
}