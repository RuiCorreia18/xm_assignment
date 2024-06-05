package com.example.xmassignment.data

import com.example.xmassignment.domain.QuestionModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestionApi {

    @GET("questions")
    fun getQuestions(): Single<List<QuestionModel>>

    @POST("question/submit")
    fun postAnswer(@Body answer: AnswerBody): Completable
}