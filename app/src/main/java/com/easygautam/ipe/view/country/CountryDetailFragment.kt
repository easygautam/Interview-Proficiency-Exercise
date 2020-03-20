package com.easygautam.ipe.view.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.easygautam.ipe.R
import com.easygautam.ipe.databinding.FragmentCountryDetailBinding

class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding
    private val viewModel by lazy {
        ViewModelProvider(activity!!).get(
            CountryActivityViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_country_detail, container, false)
        binding.fragment = this
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
