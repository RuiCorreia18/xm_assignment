package com.example.xmassignment.data

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val questionApi: QuestionApi
) {
    fun getQuestions(): Single<List<QuestionModel>> = questionApi.getQuestions()
}