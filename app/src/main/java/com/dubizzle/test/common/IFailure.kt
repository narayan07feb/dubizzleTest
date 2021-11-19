package com.dubizzle.test.common

interface IFailure {
    val errorMessage: String
}

class Failure(override val errorMessage: String) : IFailure