package com.example.jettrivia.data.data_source.remote

data class BaseResponseModel<T , E:AppError> (
    val data: T? = null,
    val error: E? = null
)