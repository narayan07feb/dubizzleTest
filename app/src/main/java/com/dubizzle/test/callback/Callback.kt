package com.dubizzle.test.callback

import android.util.Pair
import android.view.View
import com.dubizzle.test.ExcludeFromJacocoGeneratedReport

@ExcludeFromJacocoGeneratedReport
interface Callback<T> {
    fun onItemClick(item: T, vararg sharedPair: Pair<View, String>)
}
