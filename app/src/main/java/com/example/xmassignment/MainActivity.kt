package com.example.xmassignment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xmassignment.Destinations.QUESTION_PAGE
import com.example.xmassignment.Destinations.START_PAGE
import com.example.xmassignment.ui.questions.QuestionViewModel
import com.example.xmassignment.ui.questions.QuestionsScreen
import javax.inject.Inject


object Destinations {
    const val START_PAGE = "start"
    const val QUESTION_PAGE = "question"
}

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QuestionViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as MainApplication).appComponent.inject(this)

        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                Surface {
                    NavHost(navController = navController, startDestination = START_PAGE) {
                        composable(START_PAGE) {
                            InitialScreen(navController)
                        }
                        composable(QUESTION_PAGE) {
                            QuestionsScreen(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InitialScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { navController.navigate(QUESTION_PAGE) }
        ) {
            Text(text = "Start Survey")
        }
    }
}