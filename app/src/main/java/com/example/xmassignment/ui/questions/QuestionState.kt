package com.example.xmassignment.ui.questions

import com.example.xmassignment.domain.QuestionModel

data class QuestionState (
    val error: String? = null,
    val loading: Boolean = false,
    val currentQuestion: Int = 0,
    val questions: List<QuestionModel> = emptyList()
)

