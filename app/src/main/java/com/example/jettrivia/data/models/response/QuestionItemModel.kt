package com.example.jettrivia.data.models.response


data class QuestionItemModel(
    val category: String?,
    val answer: String?,
    val question: String?,
    val choices: List<String>?,
)

