package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_mideast, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate called")
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            if (currentIndex < questionBank.size - 1) {
                currentIndex = (currentIndex + 1)
            }
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val answer = questionBank[currentIndex].answer
        val messageResId = if (answer == userAnswer) {
            R.string.correct
        } else {
            R.string.incorrect
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart called ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume called ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause called ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop called ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy called ")
    }
}
