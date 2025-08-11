package com.example.jettrivia.di

import com.example.jettrivia.data.data_source.remote.QuestionApi
import com.example.jettrivia.data.repository.QuestionRepositoryImpl
import com.example.jettrivia.domain.repository.BaseQuestionRepository
import com.example.jettrivia.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
/*
This annotation tells Hilt (and Dagger) that this class is a Dagger Module
 — a container that defines how to create and provide dependencies for injection.
*/


@InstallIn(SingletonComponent::class)
/*
Specifies where this module will be installed in the Hilt dependency graph.
SingletonComponent means the provided dependencies will live as long as the entire application.
as We want a single Retrofit instance for the whole app because:
*/

object AppModule {
    @Singleton
    @Provides     // Tells Hilt how to create a dependency.
    fun provideQuestionApi(): QuestionApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionRepository(questionApi: QuestionApi): BaseQuestionRepository {
        return QuestionRepositoryImpl(questionApi)
    }
}