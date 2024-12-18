package com.appadore.machinetest

import android.app.Application


import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {






    init{
        sInstance = this
    }

    companion object{
        var sInstance: App?=null
    }



}