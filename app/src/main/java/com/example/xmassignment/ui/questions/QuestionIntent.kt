package com.example.xmassignment.ui.questions

import com.example.xmassignment.domain.QuestionModel

sealed interface QuestionIntent {
    data object FetchQuestion : QuestionIntent
    data object OnNext : QuestionIntent
    data object OnPrevious : QuestionIntent
    data class SubmitAnswer(val question: QuestionModel) : QuestionIntent
    data class SaveAnswer(val answer: String) : QuestionIntent
}