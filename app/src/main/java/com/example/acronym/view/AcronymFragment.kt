package com.example.acronym.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.acronym.R
import com.example.acronym.dataSource.Failure
import com.example.acronym.dataSource.Success
import com.example.acronym.databinding.AcronymFragmentBinding
import com.example.acronym.viewmodel.AcronymViewModel
import com.example.acronym.viewmodel.ViewModelFactory

class AcronymFragment : Fragment() {

    private lateinit var acronymViewModel: AcronymViewModel
    // Just used ViewBinding due to time crunch
    private lateinit var binding: AcronymFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AcronymFragmentBinding.inflate(layoutInflater)
        acronymViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(AcronymViewModel::class.java)
        setUp()
        return binding.root
    }

    private fun setUp() {
        binding.submit.setOnClickListener {
            acronymViewModel.getAcronyms(binding.userInput.text.toString())
        }
        acronymViewModel.acronyms.observe(
            viewLifecycleOwner,
            { response ->
                binding.resultsHeader.visibility = View.VISIBLE
                when (response) {
                    is Success ->
                        binding.results.text = if (response.acronymData.isNullOrEmpty()) {
                            getString(R.string.more_characters)
                        } else {
                            var acronymList = getString(R.string.empty_string)
                            response.acronymData[0].lfs?.forEach {
                                acronymList += "${it.lf}\n"
                            }
                            acronymList
                        }
                    is Failure -> response.message
                }
                hideKeyBoard()
            })
    }

    private fun hideKeyBoard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}