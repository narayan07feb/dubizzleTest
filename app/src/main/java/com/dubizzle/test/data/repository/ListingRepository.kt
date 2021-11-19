package com.dubizzle.test.data.repository

import com.dubizzle.test.common.IFailure
import com.dubizzle.test.common.Result
import com.dubizzle.test.data.network.safeApiCall
import com.dubizzle.test.data.network.services.ApiService
import com.dubizzle.test.domain.model.IData
import com.dubizzle.test.domain.repository.IListingRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class ListingRepository @Inject constructor(
    private val service: ApiService,
    private val dispatcher: CoroutineDispatcher
) : IListingRepository {
    override suspend fun getListing(): Result<IFailure, IData> {
        return safeApiCall(
            dispatcher = dispatcher,
            apiCall = { service.fetchResponse() },
            successTransform = {
                it
            })
    }
}