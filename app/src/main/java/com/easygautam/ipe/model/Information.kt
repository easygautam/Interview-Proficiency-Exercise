package com.easygautam.ipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Information(

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var countryId: Long,

    @field:SerializedName("imageHref")
    val imageHref: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null


)