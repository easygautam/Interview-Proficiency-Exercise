package com.easygautam.ipe.view.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.easygautam.ipe.R
import com.easygautam.ipe.databinding.ActivityCountryBinding

class CountryActivity : AppCompatActivity() {

    private val TAG by lazy { this.javaClass.simpleName }
    private lateinit var binding: ActivityCountryBinding
    private lateinit var viewModel: CountryActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_country)
        this.viewModel = ViewModelProvider(this).get(CountryActivityViewModel::class.java)
        binding.activity = this
        binding.viewModel = viewModel

        initialize()
    }


    private fun initialize() {

        addFragment(R.id.container, InformationFragment(), false)

        viewModel.country.observe(this, Observer {
            supportActionBar?.title = it.title
            Log.d(TAG, "Country loaded. $it")
        })

        viewModel.getCountry(this)
    }


    /**
     * Add fragment with animation control
     */
    fun addFragment(
        container: Int,
        fragment: Fragment,
        addToBackStack: Boolean
    ) {
        supportFragmentManager.beginTransaction().apply {
            val tag = fragment.javaClass.simpleName
            if (addToBackStack)
                addToBackStack(tag)
            add(container, fragment, tag)
        }.commit()
    }

    /**
     * Replace fragment with animation control
     */
    fun replaceFragment(
        container: Int,
        fragment: Fragment,
        addToBackStack: Boolean
    ) {
        supportFragmentManager.beginTransaction().apply {
                val tag = fragment.javaClass.simpleName
                if (addToBackStack)
                    addToBackStack(tag)
                replace(container, fragment, tag)
            }
            .commit()
    }

    fun getFragment(container: Int) = supportFragmentManager.findFragmentById(container)

}
