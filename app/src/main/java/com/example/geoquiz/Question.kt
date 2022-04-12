package com.example.geoquiz

data class Question(
    val textResId: Int,
    val answer: Boolean,
    var answeredQuestion: Boolean
)
