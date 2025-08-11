package com.example.jettrivia.data.repository

import com.example.jettrivia.data.data_source.remote.ApiCallHandler
import com.example.jettrivia.data.data_source.remote.AppError
import com.example.jettrivia.data.data_source.remote.BaseResponseModel
import com.example.jettrivia.data.data_source.remote.QuestionApi
import com.example.jettrivia.data.models.response.QuestionItemModel
import com.example.jettrivia.domain.repository.BaseQuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(private val questionApi: QuestionApi) :
    BaseQuestionRepository {

    override suspend fun getQuestionsList(): BaseResponseModel<List<QuestionItemModel>, AppError> {
        return ApiCallHandler.safeApiCall {
             questionApi.getQuestions()
        }
    }
}