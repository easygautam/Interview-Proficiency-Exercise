package com.easygautam.ipe

import android.app.Application
import com.easygautam.ipe.data.database.MRoomDatabase
import com.easygautam.ipe.data.remote.RetrofitNetwork

/**
 * Application class for the application
 */
class IpeApplication : Application() {

    // Remote API
    val api by lazy { RetrofitNetwork(this).remoteApi() }

    // Local Database
    val database by lazy { MRoomDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        lateinit var instance: IpeApplication
    }

}
