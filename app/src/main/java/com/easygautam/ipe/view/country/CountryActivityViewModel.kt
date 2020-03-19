package com.easygautam.ipe.view.country

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easygautam.ipe.Utils
import com.easygautam.ipe.model.Country
import com.easygautam.ipe.repository.CountryRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountryActivityViewModel() : ViewModel() {

    private val countryRepository = CountryRepository()
    private val disposable = CompositeDisposable()
    val country = MutableLiveData<Country>()
    val isLoading = ObservableBoolean()
    val errorMessage = ObservableField<String>()

    // Extension function to subscribe data from server and local
    private fun Single<Country>.rxCall() {
        isLoading.set(true)
        errorMessage.set(null)
        disposable.add(
            subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    country.postValue(it)
                    isLoading.set(false)
                }, {
                    it.printStackTrace()
                    errorMessage.set(it.localizedMessage)
                    isLoading.set(false)
                })
        )
    }

    /**
     * Get Country data on the basis of internet connectivity
     */
    fun getCountry(context: Context) {
        if (Utils.isNetworkAvailable(context)) {
            countryRepository.getCountryFromRemote()
                .rxCall()
        } else {
            countryRepository.getCountryFromLocal()
                .rxCall()
        }
    }


    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}