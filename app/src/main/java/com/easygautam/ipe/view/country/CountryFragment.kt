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
import com.easygautam.ipe.databinding.FragmentCountryBinding
import com.easygautam.ipe.model.Information
import com.easygautam.ipe.view.adapter.OnItemClickListener
import com.easygautam.ipe.view.adapter.RecyclerViewAdapter

/**
 * Show all details of the country
 */
class CountryFragment : Fragment() {

    private val mActivity by lazy { this.activity as CountryActivity }
    private lateinit var binding: FragmentCountryBinding

    // Activity view model in activity scope
    private val viewModel by lazy { ViewModelProvider(mActivity).get(CountryActivityViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize recycler view adapter for show country information
        val adapter = RecyclerViewAdapter<Information>(R.layout.row_information)
        binding.recyclerView.adapter = adapter
        // Add click on recycler view item
        adapter.onItemClickListener = object : OnItemClickListener<Information> {
            override fun onItemClick(view: View?, model: Information) {
                viewModel.selectedInformation.set(model)
                when (view?.id) {
                    R.id.ivEdit -> {
                        mActivity.addFragment(
                            R.id.container,
                            CountryEditFragment(), true
                        )
                    }
                    else -> {
                        mActivity.addFragment(
                            R.id.container,
                            CountryDetailFragment(), true
                        )
                    }
                }
            }
        }

        viewModel.countryInfromation.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                adapter.setList(it.filter { info -> !info.title.isNullOrBlank() && !info.description.isNullOrEmpty() })
            }
        })
    }

    /**
     * Load country data from server or local
     */
    fun loadData() {
        viewModel.loadCountry()
    }
}
