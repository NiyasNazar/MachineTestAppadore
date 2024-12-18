package com.appadore.machinetest.ui.main.flags_questions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import com.appadore.machinetest.R
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.appadore.machinetest.data.model.flags_challenge.QuestionWithCountries
import com.appadore.machinetest.data.model.flags_challenge.Questions
import com.appadore.machinetest.databinding.ActivityFlagChallengeQuestionsBinding
import com.appadore.machinetest.ui.base.BaseActivity
import com.appadore.machinetest.ui.main.TimeSettingsActivity
import com.appadore.machinetest.utils.CountriesConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type

@AndroidEntryPoint
class FlagsChallengeQuestionsActivity :
    BaseActivity<FlagChallengeViewModel, ActivityFlagChallengeQuestionsBinding>() {

    private var currentQuestionIndex: Int = 0
    private var correctAnswers: Int = 0
    private var questions: List<Questions>? = null


    private var questions2: List<QuestionWithCountries>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(
                context, FlagsChallengeQuestionsActivity::class.java
            )
            context.startActivity(starter)
        }
    }

    override fun getLayout() = R.layout.activity_flag_challenge_questions

    override fun getViewModelType() = FlagChallengeViewModel::class.java
    private fun initView() {

        viewModel.fetchQuestions()
    }

    override fun bindViews() {
        binding.viewModel = viewModel
        initView()
        //startChallengeTimer()
        //startQuestion()
        observeData()
        binding.option2Text.setOnClickListener() {
            checkAnswer(160)
        }

    }

    private fun checkAnswer(selectedCountry: Int?) {
        val question = questions!![currentQuestionIndex]
        if (selectedCountry == question.answerId) {
            Log.d(TAG, "checkAnswer: ")
            binding.option2Text.text = "Correct!"

            correctAnswers++
        } else {
            binding.option2Text.text = "Wrong!"
        }
        //  tvResult.visibility = View.VISIBLE

        // Move to the next question after a delay
        binding.option2Text.postDelayed({
            currentQuestionIndex++
            if (currentQuestionIndex < questions!!.size) {
                displayQuestion()
            } else {
                // showFinalResult()
            }
        }, 2000)
    }

    private fun observeData() {
        viewModel.questionsLiveData.observe(this@FlagsChallengeQuestionsActivity) {
            questions = it
            startChallenge()
            Log.d("observeData", "observeData: " + questions?.get(0)?.answerId)
        }
    }

    private fun startChallenge() {
        if (questions != null && questions!!.isNotEmpty()) {
            displayQuestion()
        }
    }

    private fun displayQuestion() {
        val question = questions!![currentQuestionIndex]
        Log.d("observeDatas", "observeData: " + question.answerId)
        question.answerId?.let { viewModel.getCountriesForAnswer(it) };
        viewModel.countriesLiveData.observe(this@FlagsChallengeQuestionsActivity) {
            binding.option1Text.text = it?.get(0)?.countryName
            binding.option2Text.text = it?.get(1)?.countryName
            binding.option3Text.text = it?.get(2)?.countryName
            binding.option4Text.text = it?.get(3)?.countryName

        }


        // Set the flag image (Assume you have the flag images in your drawable folder)
        /*     val resourceId = resources.getIdentifier("flag_${question.c?.lowercase()}", "drawable", packageName)
             binding.ivFlagImage.setImageResource(resourceId)
             binding.ivFlagImage.visibility = View.VISIBLE*/

        /*  tvQuestion.visibility = View.VISIBLE
          radioGroupOptions.visibility = View.VISIBLE
          tvResult.visibility = View.GONE*/

        /*
                radioGroupOptions.removeAllViews()
        */
        binding.option1Text.text =
            questions2?.get(currentQuestionIndex)?.question?.countries?.get(0)?.countryName
        binding.option2Text.text =
            questions2?.get(currentQuestionIndex)?.question?.countries?.get(1)?.countryName
        binding.option3Text.text =
            questions2?.get(currentQuestionIndex)?.question?.countries?.get(2)?.countryName
        binding.option4Text.text =
            questions2?.get(currentQuestionIndex)?.question?.countries?.get(3)?.countryName


    }


    private fun loadJSONFromAsset(): String {
        return applicationContext.assets.open("questions.json").bufferedReader()
            .use { it.readText() }
    }

    private fun parseJSONResponse(jsonResponse: String) {
        val listType: Type = object : TypeToken<List<Questions>>() {}.type
        questions = Gson().fromJson(jsonResponse, listType)
    }
}