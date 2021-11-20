package com.dubizzle.test.domain.model

import android.os.Parcelable

interface IData : Parcelable {
    val result: List<IResults>
    val pagination: IPagination
}

interface IResults : Parcelable {
    val createdAt: String
    val price: String
    val name: String
    val uid: String
    val imageIds: List<String>
    val imageUrls: List<String>
    val imageUrlsThumbnails: List<String>
}

interface IPagination : Parcelable {
    val key: String

}