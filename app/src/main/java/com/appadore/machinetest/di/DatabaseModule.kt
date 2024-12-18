package com.appadore.machinetest.di

import android.content.Context
import androidx.room.Room
import com.acube.itms.data.local.room.AppDAO
import com.acube.itms.data.local.room.AppDataBase
import com.appadore.machinetest.data.local.DataStoreHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) : AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: AppDataBase) : AppDAO {
        return dataBase.appDao()
    }
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : DataStoreHelper {
        return DataStoreHelper(context)
    }
}