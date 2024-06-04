package com.example.xmassignment.ui.questions

sealed interface QuestionIntent {
    data object FetchQuestion : QuestionIntent
    data object OnNext : QuestionIntent
    data object OnPrevious : QuestionIntent
    data class SubmitAnswer(val answer: String) : QuestionIntent
    data class SaveAnswer(val answer: String) : QuestionIntent
}