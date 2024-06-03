package com.example.xmassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xmassignment.Destinations.QUESTION_PAGE
import com.example.xmassignment.Destinations.START_PAGE
import com.example.xmassignment.ui.questions.QuestionsScreen
import com.example.xmassignment.ui.theme.XMAssignmentTheme


object Destinations {
    const val START_PAGE = "start"
    const val QUESTION_PAGE = "question"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            XMAssignmentTheme {
                Surface {
                    NavHost(navController = navController, startDestination = START_PAGE) {
                        composable(START_PAGE) {
                            InitialScreen(navController)
                        }
                        composable(QUESTION_PAGE) {
                            QuestionsScreen()
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
            onClick = { navController.navigate("questions_screen") }
        ) {
            Text(text = "Start Survey")
        }
    }
}