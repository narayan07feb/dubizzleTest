package com.dubizzle.test.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).apply(requestOptions).into(view);
    }
}

@BindingAdapter("bigImageUrl", "thumbnail")
fun loadImageWithThumbnail(view: ImageView, url: String?, thumbnailUrl: String) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).thumbnail(
            Glide.with(view).load(thumbnailUrl)
        ).apply(requestOptions).into(view);
    }
}