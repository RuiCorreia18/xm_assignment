package com.example.xmassignment.data

import com.example.xmassignment.domain.QuestionModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val questionApi: QuestionApi
) {
    fun getQuestions(): Single<List<QuestionModel>> = questionApi.getQuestions()
    fun postAnswer(answer: AnswerBody): Completable = questionApi.postAnswer(answer)
}