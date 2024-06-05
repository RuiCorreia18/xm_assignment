package com.example.xmassignment.domain

data class QuestionModel(
    val id: Int,
    val question: String,
    var isSubmitted: Boolean = false,
    var answer: String = ""
)