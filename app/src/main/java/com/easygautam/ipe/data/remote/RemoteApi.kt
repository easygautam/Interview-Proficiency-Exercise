package com.easygautam.ipe.data.remote

import com.easygautam.ipe.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteApi {

    /**
     * This API provide sample data about Canada
     */
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountry(): Single<Country>

}