package com.dubizzle.test.domain.model

interface IData {
    val result: List<IResults>
    val pagination: IPagination
}

interface IResults {
    val createdAt: String
    val price: String
    val name: String
    val uid: String
    val imageIds: List<String>
    val imageUrls: List<String>
    val imageUrlsThumbnails: List<String>
}

interface IPagination {
    val key: String

}