package com.dubizzle.test.data.network

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource
import com.dubizzle.test.common.Failure
import com.dubizzle.test.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

val countingIdlingResource = CountingIdlingResource("Network_call");
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
        Log.d("failedApi", "${ex.printStackTrace()}")
        Result.Error(Failure("Internal server error"));

    }

}