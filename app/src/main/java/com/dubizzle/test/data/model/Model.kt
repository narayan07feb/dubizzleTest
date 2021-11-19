package com.dubizzle.test.data.model

import com.dubizzle.test.domain.model.IData
import com.dubizzle.test.domain.model.IPagination
import com.dubizzle.test.domain.model.IResults

class Data(override val result: List<Results>, override val pagination: Pagination) : IData

class Results(
    override val createdAt: String,
    override val price: String,
    override val name: String,
    override val uid: String,
    override val imageIds: List<String>,
    override val imageUrls: List<String>,
    override val imageUrlsThumbnails: List<String>
) : IResults

class Pagination(override val key: String) : IPagination