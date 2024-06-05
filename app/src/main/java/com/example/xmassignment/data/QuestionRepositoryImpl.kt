package com.example.xmassignment.data

import com.example.xmassignment.domain.QuestionModel
import com.example.xmassignment.domain.QuestionRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : QuestionRepository {

    override fun getQuestions(): Single<List<QuestionModel>> {
        return remoteDataSource.getQuestions()
    }

    override fun postAnswer(question: QuestionModel): Completable {
        val answerBody = AnswerBody(
            id = question.id,
            answer = question.answer
        )
        return remoteDataSource.postAnswer(answerBody)
    }
}