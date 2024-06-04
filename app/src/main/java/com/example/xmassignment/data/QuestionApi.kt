package com.example.xmassignment.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface QuestionApi {

    @GET("questions")
    fun getQuestions(): Single<List<QuestionModel>>
}