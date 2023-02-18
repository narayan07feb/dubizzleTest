package com.dubizzle.test.app

import android.app.Application
import com.dubizzle.test.ExcludeFromJacocoGeneratedReport
import dagger.hilt.android.HiltAndroidApp

@ExcludeFromJacocoGeneratedReport
@HiltAndroidApp
class DubizzleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
