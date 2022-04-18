package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    private val quizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onActivityResult(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        restoreState(savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        trueButton.setOnClickListener {
            Toast.makeText(this, quizViewModel.checkAnswer(true), Toast.LENGTH_SHORT).show()
        }

        falseButton.setOnClickListener {
            Toast.makeText(this, quizViewModel.checkAnswer(false), Toast.LENGTH_SHORT).show()
        }

        cheatButton.setOnClickListener {
            val questionAnswer = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, questionAnswer)
            resultLauncher.launch(intent)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.text = getString(questionTextResId)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_CURRENT_INDEX, quizViewModel.currentIndex)
    }

    private fun onActivityResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val questionCheatedResult =
                intent?.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false) ?: false
            quizViewModel.setCurrentQuestionCheated(questionCheatedResult)
        }
    }

    private fun restoreState(savedState: Bundle?) {
        val currentIndex = savedState?.getInt(KEY_CURRENT_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
    }

    private fun bindViews() {
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_CURRENT_INDEX = "current_index"
    }
}
