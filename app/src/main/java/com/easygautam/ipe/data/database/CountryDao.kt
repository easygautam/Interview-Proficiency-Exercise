package com.easygautam.ipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.easygautam.ipe.model.Country

/**
 * [CountryDao] interface has all declaration about how to deal with country data in country table
 */
@Dao
interface CountryDao : BaseDao<Country> {

    @Query("select (count(*) > 0) as isAvailable from Country where title=:title")
    fun isAvailable(title: String?): Boolean

    @Query("select * from Country where title=:title")
    fun getByTitle(title: String?): Country?

    @Query("select * from Country where id=:id")
    fun getCountry(id: Long): Country

    @Query("select * from Country where id=:id")
    fun getCountryLive(id: Long): LiveData<Country?>

}