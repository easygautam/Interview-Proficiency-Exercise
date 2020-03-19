package com.easygautam.ipe

import android.app.Application
import com.easygautam.ipe.data.database.MRoomDatabase
import com.easygautam.ipe.data.remote.RetrofitNetwork

class IpeApplication : Application() {

    val api by lazy { RetrofitNetwork(this).remoteApi() }
    val database by lazy { MRoomDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        IpeApplication.instance = this

    }


    companion object {
        lateinit var instance: IpeApplication
    }

}
