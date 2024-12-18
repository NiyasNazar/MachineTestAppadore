package com.appadore.machinetest.data.model.flags_challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries_list_table")
data class Countries(


    @SerializedName("country_name") var countryName: String? = null,

    @SerializedName("id") var id: Int? = null,
    var questionId: Int? = null,
    @PrimaryKey(autoGenerate = true)
    var countryID: Int? = null

)