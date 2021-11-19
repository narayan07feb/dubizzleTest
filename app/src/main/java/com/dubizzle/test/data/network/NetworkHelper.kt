package com.dubizzle.test.data.network

import com.dubizzle.test.common.Failure
import com.dubizzle.test.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <T, X> safeApiCall(
    dispatcher: CoroutineDispatcher,
    crossinline apiCall: suspend () -> T, successTransform: (T) -> X
): Result<Failure, X> {
    return try {
        val response = withContext(dispatcher) {
            apiCall.invoke()
        }
        if (response is Response<*>) {
            if (response.isSuccessful) {
                Result.Success(successTransform(response))
            } else {
                Result.Error(Failure("Internal server error"));
            }
        } else {
            Result.Error(Failure("Internal server error"));
        }
    } catch (ex: Exception) {
        Result.Error(Failure("Internal server error"));
    }

}