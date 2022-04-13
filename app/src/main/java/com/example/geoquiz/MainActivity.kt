package com.example.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )
    private var currentIndex = 0
    private var correctAnswers = 0

    companion object {
        private const val maxPercent = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        trueButton.setOnClickListener {
            checkAnswer(true)
            questionBank[currentIndex].answeredQuestion = true
            answeredQuestionCheck()
            if (questionBank[questionBank.size - 1].answeredQuestion) {
                getCorrectAnswerPercentage()
            }
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            questionBank[currentIndex].answeredQuestion = true
            answeredQuestionCheck()
            if (questionBank[questionBank.size - 1].answeredQuestion) {
                getCorrectAnswerPercentage()
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < questionBank.size - 1) {
                currentIndex = (currentIndex + 1)
            }
            updateQuestion()
        }
        previousButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex = (currentIndex - 1)
            }
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        answeredQuestionCheck()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val answer = questionBank[currentIndex].answer
        var messageResId = 0
        if (answer == userAnswer) {
            messageResId = R.string.correct
            correctAnswers++
        } else {
            messageResId = R.string.incorrect
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }


    private fun answeredQuestionCheck() {
        val checkAnswer = questionBank[currentIndex].answeredQuestion
        if (!checkAnswer) {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
            nextButton.isEnabled = false
        } else {
            nextButton.isEnabled = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
    }

    private fun getCorrectAnswerPercentage() {
        if (currentIndex + 1 == questionBank.size) {
            val answersPercentage = (correctAnswers * maxPercent) / questionBank.size
            Toast.makeText(this, "You answered $answersPercentage% correct", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
