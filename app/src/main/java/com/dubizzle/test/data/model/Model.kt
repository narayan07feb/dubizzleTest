package com.dubizzle.test.data.model

import com.dubizzle.test.domain.model.IData
import com.dubizzle.test.domain.model.IPagination
import com.dubizzle.test.domain.model.IResults
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Data(
    @SerializedName("results")
    override val result: List<Results>,
    @SerializedName("pagination")
    override val pagination: Pagination
) : IData

@Parcelize
class Results(
    @SerializedName("created_at")
    override val createdAt: String,
    @SerializedName("price")
    override val price: String,
    @SerializedName("name")
    override val name: String,
    @SerializedName("uid")
    override val uid: String,
    @SerializedName("image_ids")
    override val imageIds: List<String>,
    @SerializedName("image_urls")
    override val imageUrls: List<String>,
    @SerializedName("image_urls_thumbnails")
    override val imageUrlsThumbnails: List<String>
) : IResults

@Parcelize
class Pagination(
    @SerializedName("key")
    override val key: String
) : IPagination