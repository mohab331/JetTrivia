package com.example.jettrivia.domain.repository

import com.example.jettrivia.data.data_source.remote.AppError
import com.example.jettrivia.data.data_source.remote.BaseResponseModel
import com.example.jettrivia.data.models.response.QuestionItemModel

interface BaseQuestionRepository {
    suspend fun getQuestionsList(): BaseResponseModel<List<QuestionItemModel>, AppError>
}