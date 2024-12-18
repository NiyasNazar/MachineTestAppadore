package com.appadore.machinetest

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.appadore.machinetest.data.model.flags_challenge.Questions
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var questions: List<Questions> = arrayListOf()
    private var currentQuestionIndex: Int = 0
    private var questionTimer: CountDownTimer? = null
    private var intervalTimer: CountDownTimer? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var questionTextView: TextView
    private lateinit var timerTextView: TextView
    private var remainingTime: Long = 30000 // 30 seconds per question
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView
    private lateinit var selectedTextView: TextView
    private var selectedId: Int? = null
    private var correctAnswers: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        questions = loadQuestions()
        questionTextView = findViewById(R.id.tv_title_schedule)
        timerTextView = findViewById(R.id.tv_count_down_timer)
        option1 = findViewById(R.id.option1Text)
        option2 = findViewById(R.id.option2Text)
        option3 = findViewById(R.id.option3Text)
        option4 = findViewById(R.id.option4Text)

        sharedPreferences = getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE)

        if (sharedPreferences.contains("current_question_index")) {
            currentQuestionIndex = sharedPreferences.getInt("current_question_index", 0)
            remainingTime = sharedPreferences.getLong(
                "remaining_time",
                30000
            ) // Default to 30 seconds if not found
            startTimer(remainingTime)
        } else {
            // Start a new quiz
            startNewQuiz()
        }
        //  restoreState()
        selectedTextView = option1
        option1.setOnClickListener { selectOption(option1) }
        option2.setOnClickListener { selectOption(option2) }
        option3.setOnClickListener { selectOption(option3) }
        option4.setOnClickListener { selectOption(option4) }

        showQuestion(currentQuestionIndex)
        //startQuestionTimer()
    }

    private fun selectOption(selected: TextView) {
        selectedId = questions.get(currentQuestionIndex).answerId
        if (::selectedTextView.isInitialized && selectedTextView != selected) {
            selectedTextView.setBackgroundResource(R.drawable.answe_options_bg)
        }

        // Set the background color for the newly selected TextView
        selected.setBackgroundResource(R.drawable.answer_options_bg_selected)

        // Update the reference to the currently selected TextView
        selectedTextView = selected
    }

    private fun startNewQuiz() {
        currentQuestionIndex = 0
        remainingTime = 30000
        showQuestion(currentQuestionIndex)
        startTimer(remainingTime)
    }

    // Load the list of questions
    private fun loadQuestions(): List<Questions> {
        return listOf(
            Questions(
                0,
                160,
                arrayListOf(
                    Countries("Bosnia and Herzegovina", 29, 0),
                    Countries("Mauritania", 142, 0),
                    Countries("Chile", 45, 0),
                    Countries("New Zealand", 160, 0),

                    ),
                "NZ"
            ),
            Questions(
                0,
                160,
                arrayListOf(
                    Countries("Apple", 29, 0),
                    Countries("Mauritania", 142, 0),
                    Countries("Chile", 45, 0),
                    Countries("New Zealand", 160, 0),

                    ),
                "Apple"
            ),
            // Add the remaining questions here...
        )
    }

    private fun showQuestion(index: Int) {
        var question = questions.get(index)

        questionTextView.text = "Which country has the code ?" +index
        // Display other details if needed
        option1.text = question.countries[0].countryName
        option2.text = question.countries[1].countryName
        option3.text = question.countries[2].countryName
        option4.text = question.countries[3].countryName
        if(index!=0){
            Log.d("Delay", "showQuestion: ")
             Handler().postDelayed({
                 option1.text = question.countries[0].countryName
                 option2.text = question.countries[1].countryName
                 option3.text = question.countries[2].countryName
                 option4.text = question.countries[3].countryName
             }, 10000)
         }else{
            Log.d("Delay", "showQuestion: ")

            option1.text = question.countries[0].countryName
             option2.text = question.countries[1].countryName
             option3.text = question.countries[2].countryName
             option4.text = question.countries[3].countryName
         }


    }

    private fun updateTimerUI(timeInMillis: Long) {
        timerTextView.text = "${timeInMillis / 1000}"
    }

    private fun startTimer(timeInMillis: Long) {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                updateTimerUI(remainingTime)
            }

            override fun onFinish() {
                moveToNextQuestion()

            }
        }.start()
    }

    /*    private fun startQuestionTimer() {
            questionTimer?.cancel() // Cancel any previous timers
            questionTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerTextView.text = "Time left: ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    currentQuestionIndex++
                    if (currentQuestionIndex < questions.size) {
                        startIntervalTimer()
                    } else {
                        finishQuiz()
                    }
                }
            }.start()
        }*/
    override fun onPause() {
        super.onPause()
        saveQuizState()
    }

    private fun saveQuizState() {
        val editor = sharedPreferences.edit()
        editor.putInt("current_question_index", currentQuestionIndex)
        editor.putLong("remaining_time", remainingTime)
        editor.apply()
    }

    private fun startIntervalTimer() {
        intervalTimer?.cancel() // Cancel any previous timers
        intervalTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Next question in: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                showQuestion(currentQuestionIndex)
                //  startQuestionTimer()
            }
        }.start()
    }

    private fun finishQuiz() {
        // Handle quiz completion
        questionTextView.text = "Quiz Completed!"
        timerTextView.visibility = View.GONE
    }

    private fun moveToNextQuestion() {
        checkAnswer(selectedId)
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            remainingTime = 30000 // Reset timer for the next question
            showQuestion(currentQuestionIndex)
            startTimer(remainingTime)
        } else {
            quizCompleted()
        }
    }

    private fun checkAnswer(selectedCountry: Int?) {
        val question = questions!![currentQuestionIndex]
        val isCorrect = selectedCountry == question.answerId
        Log.d("checkAnswer", "checkAnswer: " + isCorrect)
        when (selectedCountry) {
            question.countries[0].countryID -> option1.text =
                if (isCorrect) "Correct!" else "Wrong!"

            question.countries[1].countryID -> option2.text =
                if (isCorrect) "Correct!" else "Wrong!"

            question.countries[2].countryID -> option3.text =
                if (isCorrect) "Correct!" else "Wrong!"

            question.countries[3].countryID -> option4.text =
                if (isCorrect) "Correct!" else "Wrong!"
        }

        /*if (selectedCountry == question.answerId) {

            binding.option2Text.text = "Correct!"

            correctAnswers++
        } else {
            binding.option2Text.text = "Wrong!"
        }*/
        //  tvResult.visibility = View.VISIBLE

        // Move to the next question after a delay
        /* binding.option2Text.postDelayed({
             currentQuestionIndex++
             if (currentQuestionIndex < questions!!.size) {
                 displayQuestion()
             } else {
                 // showFinalResult()
             }
         }, 2000)*/
    }

    private fun quizCompleted() {
        Toast.makeText(this, "Quiz completed!", Toast.LENGTH_LONG).show()
        resetQuizState() // Clear saved state
    }


    override fun onStop() {
        super.onStop()
        saveState(currentQuestionIndex, getRemainingTime())
    }

    private fun resetQuizState() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun saveState(questionIndex: Int, timeElapsed: Long) {
        val editor = sharedPreferences.edit()
        editor.putInt("current_question", questionIndex)
        editor.putLong("time_elapsed", timeElapsed)
        editor.apply()
    }

    private fun restoreState() {
        currentQuestionIndex = sharedPreferences.getInt("current_question", 0)
        val timeRemaining = sharedPreferences.getLong("time_remaining", 30000L)
        startQuestionTimerWithTimeRemaining(timeRemaining)
    }

    private fun getRemainingTime(): Long {
        // Implement logic to get the remaining time on the current timer
        return 0L
    }

    private fun startQuestionTimerWithTimeRemaining(timeRemaining: Long) {
        questionTimer?.cancel()
        questionTimer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Time left: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                moveToNextQuestion()
            }
        }.start()
    }
}