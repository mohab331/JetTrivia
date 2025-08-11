package com.example.jettrivia.screens.questions

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jettrivia.navigation.Screens
import com.example.jettrivia.screens.common_widgets.ErrorView
import com.example.jettrivia.screens.common_widgets.LoadingIndicator
import com.example.jettrivia.screens.questions.components.QuestionLoadedWidget
import com.example.jettrivia.screens.questions.view_model.QuestionTriviaEvent
import com.example.jettrivia.screens.questions.view_model.QuestionsViewModel
import com.example.jettrivia.screens.questions.view_model.UiAction

@Composable
fun TriviaHomeContent(
    questionsViewModel: QuestionsViewModel = viewModel(),
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    navController: NavHostController,
) {
    val context = LocalContext.current


    /// What it does: Converts a StateFlow<T> (or any Flow) into a Compose State<T> object.
    /// a reactive variable that the UI listens to.
    val state = questionsViewModel.uiState.collectAsState()

    val selectedChoiceIndex by questionsViewModel.selectedIndex.collectAsState()
    val currentIndex by questionsViewModel.currentQuestionIndex.collectAsState()

    val isAnswerCorrect by questionsViewModel.isAnswerCorrect.collectAsState()

    /// similar to initState in flutter
    LaunchedEffect(Unit) {
        questionsViewModel.getQuestions()
        questionsViewModel.uiActions.collect { action ->
            when (action) {
                is UiAction.ShowCorrectToast -> {
                    Toast.makeText(
                        context,
                        "Correct!",
                        Toast.LENGTH_SHORT
                    ).show()
                    questionsViewModel.addQuestionToScoreMap(action.question , true)
                }

                is UiAction.ShowWrongToast -> {
                    Toast.makeText(
                        context,
                        "Wrong answer",
                        Toast.LENGTH_SHORT
                    ).show()
                    questionsViewModel.addQuestionToScoreMap(action.question , false)
                }

                is UiAction.NoSelection -> Toast.makeText(
                    context,
                    action.message,
                    Toast.LENGTH_SHORT
                ).show()

                is UiAction.Finished -> {
                    navController.navigate(Screens.ScoreScreen.route)
                }
            }
        }


    }
    Box(
        modifier = Modifier
            .padding(innerPaddingValues)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        when (state.value) {
            is QuestionTriviaEvent.Idle,
            QuestionTriviaEvent.Loading -> LoadingIndicator()

            is QuestionTriviaEvent.Error -> {
                val errorMessage = (state.value as QuestionTriviaEvent.Error).message
                ErrorView(message = errorMessage)
            }

            is QuestionTriviaEvent.Success -> {
                val items = (state.value as QuestionTriviaEvent.Success).items
                QuestionLoadedWidget(
                    items = items,
                    currentQuestionIndex = currentIndex,
                    selectedChoiceIndex = selectedChoiceIndex,
                    onChoiceSelected = { index ->
                        questionsViewModel.selectAnswer(index)
                    },
                    isAnswerCorrect = isAnswerCorrect,
                    onNextPressed = {
                        questionsViewModel.onNextPressed(items)
                    }
                )
            }

        }
    }
}