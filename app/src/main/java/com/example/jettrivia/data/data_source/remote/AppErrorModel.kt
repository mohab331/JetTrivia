package com.example.jettrivia.data.data_source.remote

import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed interface AppError {
    val cause: Throwable?
    data class Network(override val cause: Throwable? = null) : AppError
    data class Http(val code: Int, override val cause: Throwable? = null) : AppError
    data class Timeout(override val cause: Throwable? = null) : AppError
    data class Parsing(override val cause: Throwable? = null) : AppError
    data class Unknown(override val cause: Throwable? = null) : AppError
}

fun Throwable.toAppError(): AppError = when (this) {
    is SocketTimeoutException -> AppError.Timeout(this)
    is IOException -> AppError.Network(this) // no connection, DNS, etc.
    is HttpException -> AppError.Http(this.code(), this) // 4xx/5xx
    is JsonSyntaxException -> AppError.Parsing(this) // Gson parsing
    else -> AppError.Unknown(this)
}

fun AppError.toUserMessage(isArabic: Boolean = true): String = when (this) {
    is AppError.Network ->
        if (isArabic) "لا يوجد اتصال بالإنترنت. حاول مرة أخرى."
        else "No internet connection. Please try again."

    is AppError.Timeout ->
        if (isArabic) "المهلة انتهت. يرجى المحاولة لاحقًا."
        else "Request timed out. Please try again later."

    is AppError.Http ->
        if (isArabic) "خطأ من الخادم (${this.code}). حاول لاحقًا."
        else "Server error (${this.code}). Please try again later."

    is AppError.Parsing ->
        if (isArabic) "حدث خطأ أثناء قراءة البيانات."
        else "An error occurred while reading the data."

    is AppError.Unknown ->
        if (isArabic) "حدث خطأ غير متوقع."
        else "An unexpected error occurred."
}

