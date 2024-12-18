package com.appadore.machinetest.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.appadore.machinetest.R
import com.appadore.machinetest.databinding.ActivityTimeSettingsBinding
import com.appadore.machinetest.ui.base.BaseActivity
import com.appadore.machinetest.ui.main.challenge_setup.ChallengeSetupViewModel
import com.appadore.machinetest.ui.main.flags_questions.FlagChallengeViewModel
import com.appadore.machinetest.ui.main.flags_questions.FlagsChallengeQuestionsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeSettingsActivity : BaseActivity<ChallengeSetupViewModel, ActivityTimeSettingsBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

    }

    override fun getLayout() = R.layout.activity_time_settings

    override fun getViewModelType() = ChallengeSetupViewModel::class.java

    override fun bindViews() {
        binding.viewModel = viewModel
        observeData()
    }

    private fun observeData() {
        viewModel.successLiveData.observe(this@TimeSettingsActivity){
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                FlagsChallengeQuestionsActivity.start(this)
            }
        }
    }


}