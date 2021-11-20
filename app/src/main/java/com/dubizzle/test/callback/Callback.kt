package com.dubizzle.test.callback

import androidx.databinding.ViewDataBinding

interface Callback<T, B : ViewDataBinding> {
    fun onItemClick(item: T, binding: B)
}