package com.easygautam.ipe.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.easygautam.ipe.Const
import com.google.gson.annotations.SerializedName

@Entity
data class Country(

    @PrimaryKey()
    var id: Long = Const.DEFAULT_COUNTRY_ID,

    @field:SerializedName("title")
    var title: String? = null,


    @field:SerializedName("rows")
    @Ignore
    var rows: List<Information> = emptyList()

)