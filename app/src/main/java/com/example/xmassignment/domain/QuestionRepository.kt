package com.example.xmassignment.domain

import com.example.xmassignment.data.QuestionModel
import io.reactivex.rxjava3.core.Single

interface QuestionRepository {
    fun getQuestions() : Single<List<QuestionModel>>
}