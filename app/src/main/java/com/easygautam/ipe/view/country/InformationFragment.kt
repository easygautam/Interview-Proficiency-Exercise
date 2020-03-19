package com.easygautam.ipe.view.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.easygautam.ipe.R
import com.easygautam.ipe.databinding.FragmentInformationBinding
import com.easygautam.ipe.model.Information
import com.easygautam.ipe.view.adapter.RecyclerViewAdapter

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private val viewModel by lazy { ViewModelProvider(activity!!).get(CountryActivityViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.country.observe(viewLifecycleOwner, Observer {
            val adapter = RecyclerViewAdapter<Information>(R.layout.row_information)
            if (it.rows.isNotEmpty()) {
                adapter.setList(it.rows.filter { info -> !info.title.isNullOrBlank() && !info.description.isNullOrEmpty() })
            } else {
                viewModel.errorMessage.set("No information available for ${it.title}")
            }
            binding.recyclerView.adapter = adapter
        })

    }

    fun loadData() {
        viewModel.getCountry(activity!!)
    }
}
