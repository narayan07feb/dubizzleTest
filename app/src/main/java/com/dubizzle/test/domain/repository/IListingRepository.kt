package com.dubizzle.test.domain.repository

import com.dubizzle.test.common.IFailure
import com.dubizzle.test.common.Result
import com.dubizzle.test.domain.model.IData

interface IListingRepository {
    suspend fun getListing(): Result<IFailure, IData>
}