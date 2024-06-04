package com.example.xmassignment.domain

data class QuestionModel(
    val id: Int,
    val question: String,
    val isSubmitted: Boolean = false,
    var answer: String?
)