package com.acube.itms.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appadore.machinetest.data.model.flags_challenge.Countries

import com.appadore.machinetest.data.model.flags_challenge.Questions

@Database(
    entities = [
        Questions::class,Countries::class,

    ], version = 2
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun appDao(): AppDAO
}