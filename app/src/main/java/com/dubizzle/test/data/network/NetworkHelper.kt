package com.dubizzle.test.data.network

import com.dubizzle.test.common.Failure
import com.dubizzle.test.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend inline fun <T, reified X> safeApiCall(
    dispatcher: CoroutineDispatcher,
    crossinline apiCall: suspend () -> T, successTransform: (T) -> X
): Result<Failure, X> {
    return try {
        val response = withContext(dispatcher) {
            apiCall.invoke()
        }
        if (response is X) {
            Result.Success(successTransform(response))
        } else {
            Result.Error(Failure("Internal server error"));
        }
    } catch (ex: Exception) {
        Result.Error(Failure("Internal server error"));
    }

}