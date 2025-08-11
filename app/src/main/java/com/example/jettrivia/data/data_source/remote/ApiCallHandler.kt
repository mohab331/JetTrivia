package com.example.jettrivia.data.data_source.remote

/// a singleton helper
object ApiCallHandler {
    suspend fun <T> safeApiCall(
        block: suspend () -> T
    ): BaseResponseModel<T, AppError> = try {
        BaseResponseModel(data = block(), error = null)
    } catch (t: Throwable) {
        BaseResponseModel(data = null, error = t.toAppError())
    }
}