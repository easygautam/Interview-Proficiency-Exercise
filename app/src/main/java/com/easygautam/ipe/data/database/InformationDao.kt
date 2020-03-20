package com.easygautam.ipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.easygautam.ipe.model.Information

/**
 * [InformationDao] interface has all declaration about how to deal with information data of country in information table
 */
@Dao
abstract class InformationDao : BaseDao<Information> {

    @Query("select * from Information where countryId=:countryId and imageHref=:imageHref and description=:description and title=:title")
    abstract fun getByProperties(
        countryId: Long,
        imageHref: String?,
        description: String?,
        title: String?
    ): Information?

    private fun getByProperties(information: Information): Information? {
        return with(information) {
            getByProperties(countryId, imageHref, description, title)
        }
    }

    fun save(information: List<Information>) {
        information.forEach {
            getByProperties(it)?.let { existInformation ->
                it.id = existInformation.id
                update(it)
            } ?: insert(it)
        }
    }

    @Query("select * from Information where countryId=:countryId")
    abstract fun getAllInformationByCountryId(
        countryId: Long
    ): LiveData<List<Information>>

}