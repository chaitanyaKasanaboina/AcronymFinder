package com.example.acronym.dataSource


sealed class AcronymResponse
data class Success(val acronymData: AcronymData) : AcronymResponse()
data class Failure(val message: String, val cause: Throwable? = null) : AcronymResponse()
