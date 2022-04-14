package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    companion object {
        private const val KEY_CURRENT_INDEX = "index"
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(R.layout.activity_main)

        restoreState(savedInstanceState?.getInt(KEY_CURRENT_INDEX, 0) ?: 0)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        trueButton.setOnClickListener {
            Toast.makeText(this, quizViewModel.checkAnswer(true), Toast.LENGTH_SHORT).show()
        }

        falseButton.setOnClickListener {
            Toast.makeText(this, quizViewModel.checkAnswer(false), Toast.LENGTH_SHORT).show()
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

    private fun restoreState(currentIndex: Int) {
        quizViewModel.currentIndex = currentIndex
    }
}
