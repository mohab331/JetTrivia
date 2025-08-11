package com.example.jettrivia.screens.questions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jettrivia.screens.questions.view_model.QuestionsViewModel
import com.example.jettrivia.utils.AppColors

@Composable
fun TriviaHomeScreen(
    navController: NavHostController,
    questionsViewModel: QuestionsViewModel,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.mDarkPurple
    ) { innerPadding ->
        TriviaHomeContent(
            innerPaddingValues = innerPadding,
            questionsViewModel = questionsViewModel,
            navController = navController,
        )
    }
}