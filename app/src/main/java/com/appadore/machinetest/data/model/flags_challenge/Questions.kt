package com.appadore.machinetest.data.model.flags_challenge

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appadore.machinetest.utils.CountriesConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "questions_list_table")

data class Questions(
    @PrimaryKey
    var Id: Int? = null,


    @SerializedName("answer_id") var answerId: Int? = null,
 @Ignore
    @TypeConverters(CountriesConverter::class)
    @SerializedName("countries") var countries: ArrayList<Countries> = arrayListOf(),
    @SerializedName("country_code") var countryCode: String? = null
)