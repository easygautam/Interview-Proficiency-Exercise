package com.easygautam.ipe.view.country

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.easygautam.ipe.model.Country
import com.easygautam.ipe.model.Information
import com.easygautam.ipe.repository.CountryRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountryActivityViewModel : ViewModel() {

    private val countryRepository = CountryRepository()
    private val disposable = CompositeDisposable()

    val country = countryRepository.getCountry()
    val countryInfromation = countryRepository.getCountryInformation()

    val isLoading = ObservableBoolean()
    val errorMessage = ObservableField<String>()

    val selectedInformation = ObservableField<Information>()


    // Extension function to subscribe data from server
    private fun Single<Country>.rxCallRemote() {
        isLoading.set(true)
        errorMessage.set(null)
        disposable.add(
            subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.set(false)
                }, {
                    it.printStackTrace()
                    errorMessage.set(it.localizedMessage)
                    isLoading.set(false)
                })
        )
    }


    /**
     * Load Country data from remote.
     */
    fun loadCountry() {
        countryRepository.getCountryFromRemote()
            .rxCallRemote()
    }

    /**
     * Now we don't have any API to update data on server so for now only updating UI in list and local database
     * Once you refresh the data then changes will not be destroyed
     */
    fun saveInformation() {
        selectedInformation.get()?.let {
            countryRepository.updateInformation(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}