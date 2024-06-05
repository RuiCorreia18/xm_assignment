package com.example.xmassignment.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface QuestionRepository {
    fun getQuestions(): Single<List<QuestionModel>>
    fun postAnswer(question: QuestionModel): Completable
}