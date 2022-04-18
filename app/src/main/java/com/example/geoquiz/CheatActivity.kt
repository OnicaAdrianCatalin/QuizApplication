package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showApiLevelTextView: TextView
    private lateinit var showAnswerButton: Button
    private var questionAnswer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        bindViews()
        showApiLevelTextView.text = getString(R.string.api_level, Build.VERSION.SDK_INT.toString())
        questionAnswer = intent.getBooleanExtra(EXTRA_ANSWER_QUESTION, false)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        showAnswerButton.setOnClickListener {
            val answerText = when {
                questionAnswer -> R.string._true
                else -> R.string._false
            }
            answerTextView.text = getString(answerText)
            setAnswerShownResult()
        }
    }

    private fun setAnswerShownResult() {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
        }
        setResult(Activity.RESULT_OK, data)
    }

    private fun bindViews() {
        answerTextView = findViewById(R.id.answer_text_view)
        showApiLevelTextView = findViewById(R.id.show_api_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
    }

    companion object {
        const val EXTRA_ANSWER_QUESTION = "com.example.geoquiz.answer_question"
        const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

        fun newIntent(packageContext: Context, questionAnswer: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_QUESTION, questionAnswer)
            }
        }
    }
}
