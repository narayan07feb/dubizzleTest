package com.dubizzle.test.data.network.services

import com.dubizzle.test.data.model.Data
import retrofit2.http.GET

interface ApiService {
    @GET("/default/dynamodb-writer")
    suspend fun fetchResponse(): Data
}