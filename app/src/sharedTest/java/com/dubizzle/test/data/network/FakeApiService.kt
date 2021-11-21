package com.dubizzle.test.data.network

import com.dubizzle.JsonProvider
import com.dubizzle.test.data.model.Data
import com.dubizzle.test.data.network.services.ApiService

class FakeApiService : ApiService {
    var failUserApi: Boolean = false
    override suspend fun fetchResponse(): Data {

        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: Data = JsonProvider.objectFromJsonFileWithType(response)
        return fakeResponse
    }

    companion object {
        private const val response = "/json/success.json"
    }
}