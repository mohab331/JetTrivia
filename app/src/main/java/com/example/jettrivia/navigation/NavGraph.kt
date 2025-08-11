package com.example.jettrivia.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jettrivia.screens.questions.TriviaHomeScreen
import com.example.jettrivia.screens.questions.view_model.QuestionsViewModel
import com.example.jettrivia.screens.score.ScoreScreen

@Composable
fun TriviaNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.TriviaHomeScreen.route
) {

    val questionsViewModel: QuestionsViewModel = viewModel<QuestionsViewModel>()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.TriviaHomeScreen.route) {
            TriviaHomeScreen(navController, questionsViewModel = questionsViewModel)
        }
        composable(
            route = Screens.ScoreScreen.route,
        ) {
            ScoreScreen(navController, questionsViewModel = questionsViewModel)
        }
    }
}