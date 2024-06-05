package com.example.xmassignment.ui.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xmassignment.Destinations.START_PAGE


@Composable
fun QuestionsScreen(questionViewModel: QuestionViewModel, navController: NavHostController) {

    val state by questionViewModel.state.collectAsState()


    LaunchedEffect(questionViewModel) {
        questionViewModel.handleIntent(QuestionIntent.FetchQuestion)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(CenterHorizontally)
                )
            }

            state.error != null -> {
                Text(text = "Error: ${state.error}")
            }

            state.questions.isNotEmpty() -> {

                val question = state.questions[state.currentQuestion]
                var answer by remember { mutableStateOf("") }
                answer = question.answer

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate(START_PAGE) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    val currentQuestionIndex = question.id
                    val totalQuestions = state.questions.size

                    Text(text = "Question ${currentQuestionIndex}/$totalQuestions")
                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            answer = ""
                            questionViewModel.handleIntent(QuestionIntent.OnPrevious)
                        },
                        enabled = question.id > 1
                    ) {
                        Text(text = "Previous")
                    }

                    Button(
                        onClick = {
                            answer = ""
                            questionViewModel.handleIntent(QuestionIntent.OnNext)
                        },
                        enabled = currentQuestionIndex < totalQuestions
                    ) {
                        Text(text = "Next")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                val questionsSubmitted = state.questions.count { it.isSubmitted }
                // Questions Submitted
                Text(
                    text = "Questions submitted: $questionsSubmitted",
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Question Text
                Text(text = question.question)
                Spacer(modifier = Modifier.height(16.dp))


                // Answer Textfield
                TextField(
                    value = answer,
                    onValueChange = { newValue ->
                        answer = newValue
                        questionViewModel.handleIntent(QuestionIntent.SaveAnswer(newValue))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                val isSubmitted = question.isSubmitted
                Button(
                    onClick = { questionViewModel.handleIntent(QuestionIntent.SubmitAnswer(question)) },
                    enabled = !isSubmitted,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (isSubmitted) "Already submitted" else "Submit")
                }
            }
        }
    }
}
