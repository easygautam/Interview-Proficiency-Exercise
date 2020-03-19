package com.easygautam.ipe.data.remote

import android.content.Context
import com.easygautam.ipe.Const
import com.easygautam.ipe.Utils
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [RetrofitNetwork] class is used to build retrofit client and provide method
 * to create [RemoteApi] instance.
 */
class RetrofitNetwork(private val context: Context) {
    private lateinit var retrofit: Retrofit

    init {
        initRetrofit()
    }

    // Init retrofit client
    private fun initRetrofit() {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                if (!Utils.isNetworkAvailable(context))
                    throw NoInternetConnectionException()
                return chain.proceed(chain.request())
            }

        })
        val client = builder.build()

//      Retrofit client has used Gson convertor and RxJava2 for thread management.
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Const.REMOTE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * Call this method to get [RemoteApi] instance.
     */
    fun remoteApi(): RemoteApi = retrofit.create(RemoteApi::class.java)

}

class NoInternetConnectionException : Exception()
