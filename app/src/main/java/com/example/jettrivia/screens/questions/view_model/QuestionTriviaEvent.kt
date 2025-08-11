package com.example.jettrivia.screens.questions.view_model

import com.example.jettrivia.data.models.response.QuestionItemModel


sealed interface QuestionTriviaEvent {
    data object Idle : QuestionTriviaEvent
    data object Loading : QuestionTriviaEvent
    data class Success(val items: List<QuestionItemModel>) : QuestionTriviaEvent
    data class Error(val message: String) : QuestionTriviaEvent
}