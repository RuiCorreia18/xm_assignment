package com.example.xmassignment.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmassignment.domain.QuestionModel
import com.example.xmassignment.domain.QuestionRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(QuestionState(questions = emptyList()))
    val state: StateFlow<QuestionState>
        get() = _state

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun handleIntent(intent: QuestionIntent) {
        viewModelScope.launch {
            when (intent) {
                is QuestionIntent.SaveAnswer -> {
                    updateQuestionAnswer(intent)
                }

                is QuestionIntent.SubmitAnswer -> {
                    submitAnswer(intent.question)
                }

                QuestionIntent.FetchQuestion -> fetchQuestions()
                QuestionIntent.OnNext -> incrementCurrentQuestion()
                QuestionIntent.OnPrevious -> decrementCurrentQuestion()
            }
        }
    }

    private fun updateQuestionAnswer(intent: QuestionIntent.SaveAnswer) {
        val questionIndex = state.value.currentQuestion
        val question = state.value.questions[questionIndex]
        val questionList = _state.value.questions
        questionList.find { it.id == question.id }
            ?.let { it.answer = intent.answer }

        _state.value = QuestionState(
            questions = questionList,
            loading = false
        )
    }

    private fun decrementCurrentQuestion() {
        val nextQuestion = state.value.currentQuestion - 1
        _state.value = _state.value.copy(
            currentQuestion = nextQuestion
        )
    }

    private fun incrementCurrentQuestion() {
        val nextQuestion = state.value.currentQuestion + 1
        _state.value = _state.value.copy(
            currentQuestion = nextQuestion
        )
    }

    private fun fetchQuestions() {
        repository.getQuestions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.value = QuestionState(loading = true) }
            .subscribeBy(
                onSuccess = { _state.value = QuestionState(questions = it) },
                onError = { showErrorMessage(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun submitAnswer(question: QuestionModel) {
        repository.postAnswer(question)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { updateQuestionIsSubmitted(question) },
                onError = { showErrorMessage(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun showErrorMessage(it: Throwable) {
        _state.value = QuestionState(error = it.message)
    }

    private fun updateQuestionIsSubmitted(question: QuestionModel) {
        val questionList = _state.value.questions
        questionList.find { it.id == question.id }
            ?.let { it.isSubmitted = true }

        _state.value = QuestionState(
            questions = questionList
        )
    }
}