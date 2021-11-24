package com.example.acronym.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acronym.dataSource.AcronymListDataSource

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AcronymViewModel::class.java)) {
            return AcronymViewModel(
                acronymListDataSource =  AcronymListDataSource()
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}