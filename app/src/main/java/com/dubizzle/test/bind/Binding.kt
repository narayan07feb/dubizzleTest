package com.dubizzle.test.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(view);
    }
}

@BindingAdapter("bigImageUrl", "thumbnail")
fun loadImageWithThumbnail(view: ImageView, url: String?, thumbnailUrl: String) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).thumbnail(
            Glide.with(view).load(thumbnailUrl)
        ).diskCacheStrategy(DiskCacheStrategy.DATA).into(view);
    }
}