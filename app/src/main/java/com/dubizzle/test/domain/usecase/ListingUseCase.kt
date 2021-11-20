package com.dubizzle.test.domain.usecase

import com.dubizzle.test.common.IFailure
import com.dubizzle.test.common.Result
import com.dubizzle.test.domain.model.IData
import com.dubizzle.test.domain.repository.IListingRepository
import com.dubizzle.test.domain.usecase.base.UseCase
import javax.inject.Inject

class ListingUseCase @Inject constructor(private val repo: IListingRepository) :
    UseCase<Unit, IData>() {
    override suspend fun run(params: Unit): Result<IFailure, IData> {
        return repo.getListing();
    }
}