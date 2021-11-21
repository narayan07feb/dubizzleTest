package com.dubizzle.test.callback

import android.util.Pair
import android.view.View

interface Callback<T> {
    fun onItemClick(item: T, vararg sharedPair: Pair<View, String>)
}