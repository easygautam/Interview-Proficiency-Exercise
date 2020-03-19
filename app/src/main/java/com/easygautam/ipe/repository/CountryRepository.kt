package com.easygautam.ipe.repository

import com.easygautam.ipe.Const
import com.easygautam.ipe.IpeApplication
import com.easygautam.ipe.model.Country
import io.reactivex.Single

class CountryRepository {

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

    fun getCountryFromLocal(): Single<Country> {
        return with(IpeApplication.instance) {
            database.countryDao().getCountryRx(Const.DEFAULT_COUNTRY_ID).map {
                it.rows = database.informationDao().getAllInformationByCountryId(it.id)
                it
            }
        }
    }

}