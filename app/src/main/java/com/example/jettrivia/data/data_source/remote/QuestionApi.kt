package com.example.jettrivia.data.data_source.remote

import com.example.jettrivia.data.models.response.QuestionItemModel
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton

interface QuestionApi {
    @GET("world.json")
    suspend fun getQuestions(): List<QuestionItemModel>
}