package com.appadore.machinetest.data.model.repository



import com.acube.itms.data.local.room.AppDAO

import com.appadore.machinetest.data.local.DataStoreHelper




abstract class BaseRepository {
    abstract fun getDataStoreHelper(): DataStoreHelper
    abstract fun getRoomDao(): AppDAO












}