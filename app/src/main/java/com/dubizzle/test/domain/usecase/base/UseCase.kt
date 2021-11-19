package com.dubizzle.test.domain.usecase.base

import com.dubizzle.test.common.Result
import com.dubizzle.test.common.IFailure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<in Params, out Type> where Type : Any? {
    abstract suspend fun run(params: Params): Result<IFailure, Type>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (IFailure) -> Unit = {}
    ) {
        val job = scope.async {
            run(params)
        }
        scope.launch {
            job.await().transform(onFailure, onSuccess)
        }
    }


}