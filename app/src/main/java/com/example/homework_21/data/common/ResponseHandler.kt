package com.example.homework_21.data.common

import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ResponseHandler {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = flow {
        emit(Resource.Loading(loading = true))
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(data = body))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Error Occurred"
                emit(Resource.Error(errorMessage = errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(errorMessage = e.message ?: "Error Occurred"))
        }
        emit(Resource.Loading(loading = false))
    }
}