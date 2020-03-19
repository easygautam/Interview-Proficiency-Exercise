package com.easygautam.ipe.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Functions to perform basic database operations
 */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(t: List<T>)

    @Delete
    fun delete(t: T)

    @Delete
    fun delete(t: List<T>)



}