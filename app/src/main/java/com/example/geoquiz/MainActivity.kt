package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener {
            // CHALLENGE: Customize the toast to show at the top instead of the bottom of the screen
            val truePopUp = Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG)
            truePopUp.setGravity(Gravity.TOP,0,0)
            truePopUp.show()
        }
        falseButton.setOnClickListener {
            //CHALLENGE *
            val falsePopUp = Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG)
            falsePopUp.setGravity(Gravity.TOP,0,0)
            falsePopUp.show()
        }
    }
}
