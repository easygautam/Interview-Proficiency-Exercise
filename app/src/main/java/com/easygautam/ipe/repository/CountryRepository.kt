package com.easygautam.ipe.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.easygautam.ipe.Const
import com.easygautam.ipe.IpeApplication
import com.easygautam.ipe.model.Country
import com.easygautam.ipe.model.Information
import io.reactivex.Single

class CountryRepository {

    /**
     * Get country from server and store into local database
     */
    fun getCountryFromRemote(): Single<Country> {
        return with(IpeApplication.instance) {
            api.getCountry()
                .doOnSuccess { country ->
                    database.countryDao().apply {
                        // Check if country is already not available then insert
                        if (!isAvailable(country.title))
                            insert(country)
                    }
                    database.informationDao().apply {
                        // Save or Update all information of country
                        save(country.rows.map {
                            it.countryId = country.id
                            it
                        })
                    }
                }
        }
    }

    /**
     * Get country from local database
     */
    fun getCountry(): LiveData<Country?> {
        return with(IpeApplication.instance) {
            database.countryDao().getCountryLive(Const.DEFAULT_COUNTRY_ID)
        }
    }

    /**
     * Get all country information from local database
     */
    fun getCountryInformation(): LiveData<List<Information>> {
        return with(IpeApplication.instance) {
            database.informationDao().getAllInformationByCountryId(Const.DEFAULT_COUNTRY_ID)
        }
    }

    /**
     * update country information into local database
     */
    fun updateInformation(information: Information) {
        with(IpeApplication.instance) {
            AsyncTask.execute { database.informationDao().update(information) }
        }
    }

}