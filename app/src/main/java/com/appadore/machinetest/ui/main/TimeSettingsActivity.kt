package com.appadore.machinetest.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import com.appadore.machinetest.R
import com.appadore.machinetest.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeSettingsActivity :  BaseActivity<AuthViewModel, ActivityAuthBinding>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_settings)

    }

    override fun getLayout() = R.layout.activity_time_settings

    override fun getViewModelType(): Class<AuthViewModel> {
        TODO("Not yet implemented")
    }

    override fun bindViews() {
        TODO("Not yet implemented")
    }

    override fun trackSessionData(): LiveData<Boolean>? {
        TODO("Not yet implemented")
    }
}