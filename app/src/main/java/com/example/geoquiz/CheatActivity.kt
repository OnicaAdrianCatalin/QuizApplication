package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private var questionAnswer = false
    private var answerText = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        bindViews()
        questionAnswer = intent.getBooleanExtra(EXTRA_ANSWER_QUESTION, false)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        showAnswerButton.setOnClickListener {
            answerText = when {
                questionAnswer -> R.string._true
                else -> R.string._false
            }
            setAnswerShownResult()
            answerTextView.text = getString(answerText)
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
        showAnswerButton = findViewById(R.id.show_answer_button)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_CURRENT_ANSWER, answerText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        answerText = savedInstanceState.getInt(KEY_CURRENT_ANSWER, 0)
        super.onRestoreInstanceState(savedInstanceState)
        answerTextView.text = getString(answerText)
    }

    companion object {
        private const val KEY_CURRENT_ANSWER = "current_answer"
        private const val EXTRA_ANSWER_QUESTION = "com.example.geoquiz.answer_question"
        const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

        fun newIntent(packageContext: Context, questionAnswer: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_QUESTION, questionAnswer)
            }
        }
    }
}
