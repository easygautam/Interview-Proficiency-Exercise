package com.easygautam.ipe.model

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Information(

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var countryId: Long,

    @field:SerializedName("imageHref")
    var imageHref: String? = null,

    @field:SerializedName("description")
    var description: String? = null,

    @field:SerializedName("title")
    var title: String? = null


): BaseObservable()