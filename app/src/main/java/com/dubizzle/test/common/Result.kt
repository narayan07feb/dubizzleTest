package com.dubizzle.test.common

sealed class Result<out E, out RS> where RS : Any? {
    data class Error<out E>(val errorVal: E) : Result<E, Nothing>()
    data class Success<out RS>(val successsVal: RS) : Result<Nothing, RS>()

    val isError get() = this is Error<E>

    val isSuccess get() = this is Success<RS>

    fun transform(fnL: (E) -> Any, fnR: (RS) -> Any): Any {
        return when (this) {
            is Error ->
                fnL(errorVal)
            is Success ->
                fnR(successsVal)
        }
    }

}