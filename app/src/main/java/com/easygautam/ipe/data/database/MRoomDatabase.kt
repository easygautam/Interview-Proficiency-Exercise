package com.easygautam.ipe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.easygautam.ipe.Const
import com.easygautam.ipe.model.Country
import com.easygautam.ipe.model.Information


@Database(
    entities = [
        Country::class,
        Information::class
    ],
    version = Const.DATABASE_VERSION_CODE,
    exportSchema = false
)
abstract class MRoomDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
    abstract fun informationDao(): InformationDao

    companion object {

        private const val NAME = Const.DATABASE_NAME

        @Volatile
        private var instance: MRoomDatabase? = null

        fun getInstance(context: Context): MRoomDatabase {
            if (instance != null) return instance!!
            synchronized(MRoomDatabase::class.java) {
                instance = Room.databaseBuilder(context, MRoomDatabase::class.java, NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                return instance!!
            }
        }
    }

}