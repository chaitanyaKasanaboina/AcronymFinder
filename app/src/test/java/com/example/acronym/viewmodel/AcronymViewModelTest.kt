package com.example.acronym.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.acronym.dataSource.AcronymData
import com.example.acronym.dataSource.AcronymListDataSource
import com.example.acronym.dataSource.ResponseCallback
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AcronymViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var acronymViewModel : AcronymViewModel
    private val acronymListDataSource = mockkClass(AcronymListDataSource::class)


    @Before
    fun setUp() {
        acronymViewModel = AcronymViewModel(acronymListDataSource)
    }

    // just wrote single test case to showcase the unit testing knowledge due to time crunch
    @Test
    fun getAcronyms() {
        every {  acronymListDataSource.getAcronyms(any(), any())} answers {
            secondArg<ResponseCallback>().onSuccess(AcronymData())
        }
        acronymViewModel.getAcronyms("asd")
        Assert.assertNotNull(acronymViewModel.acronyms.value)
    }
}