package com.easygautam.ipe.view.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.easygautam.ipe.R
import com.easygautam.ipe.databinding.FragmentCountryEditBinding

class CountryEditFragment : Fragment() {

    private val mActivity by lazy { this.activity as CountryActivity }
    private lateinit var binding: FragmentCountryEditBinding
    private val viewModel by lazy {
        ViewModelProvider(mActivity).get(
            CountryActivityViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_country_edit, container, false)
        binding.fragment = this
        binding.viewModel = this.viewModel
        return binding.root
    }

    fun save() {
        this.viewModel.saveInformation()
        this.mActivity.onBackPressed()
    }

}
