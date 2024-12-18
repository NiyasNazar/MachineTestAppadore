package com.appadore.machinetest.utils

import androidx.room.TypeConverter
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountriesConverter {
    @TypeConverter
    fun fromCountriesList(value: ArrayList<Countries>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Countries>>() {}.type
        return gson.toJson(value, type)
    }
    @TypeConverter
    fun toCountriesList(value: String): ArrayList<Countries> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Countries>>() {}.type
        return gson.fromJson(value, type)
    }
}