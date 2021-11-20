package com.dubizzle.test.callback

interface Callback<T> {
    fun onItemClick(item: T)
}