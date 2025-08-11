package com.example.jettrivia.screens.questions.view_model

import com.example.jettrivia.data.models.response.QuestionItemModel

sealed interface UiAction {
    data class ShowCorrectToast(val question: QuestionItemModel) : UiAction
    data class ShowWrongToast(val question: QuestionItemModel) : UiAction
    data class NoSelection(val message:String) : UiAction
    data object Finished: UiAction

}