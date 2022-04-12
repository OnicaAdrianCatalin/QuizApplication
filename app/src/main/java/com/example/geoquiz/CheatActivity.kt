package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private lateinit var answerTextView: TextView
private lateinit var showAnswerButton: Button
private val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
 val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
private var answerIsTrue = false

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        setOnClickListeners()

    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    private fun setOnClickListeners(){
        showAnswerButton.setOnClickListener {
            val answerText = when{
                answerIsTrue -> R.string._true
                else -> R.string._false
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }
    fun setAnswerShownResult(isAnswerShown:Boolean){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown)
        }
        setResult(Activity.RESULT_OK,data)
    }
}