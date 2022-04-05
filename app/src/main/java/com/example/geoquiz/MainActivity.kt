package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate called ")
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener {
            checkAnswer(true)
            questionBank[currentIndex].answeredQuestion = true
            answeredQuestionCheck()
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            questionBank[currentIndex].answeredQuestion = true
            answeredQuestionCheck()
        }

        nextButton.setOnClickListener {
            if (currentIndex < questionBank.size-1) {
                currentIndex = (currentIndex + 1)
            }
            if(questionBank[questionBank.size-1].answeredQuestion == true){
                getCorrectAnswerPercentage()
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
            messageResId = R.string.correct_toast
            correctAnswers++
        } else {
            messageResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }
        //CHALLENGE: DISABLE THE BUTTONS TO PREVENT MULTIPLE ANSWERS
    private fun answeredQuestionCheck() {
        var checkAnswer = questionBank[currentIndex].answeredQuestion
        if (checkAnswer == false) {
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
        } else {
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
        }
    }
 // CHALLENGE: CALCULATE THE PERCENTAGE OF CORRECT ANSWERS
    private fun getCorrectAnswerPercentage() {
       var answersPercentage = 0
        if(currentIndex+1 == questionBank.size){
            answersPercentage = (correctAnswers * 100) / questionBank.size
        Toast.makeText(this,"You answered $answersPercentage% correct",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart called ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume called ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause called ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop called ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy called ")
    }

}