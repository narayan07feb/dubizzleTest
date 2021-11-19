package com.dubizzle.test.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dubizzle.test.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingActivity : AppCompatActivity() {

    private val dataViewModel: DataViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataViewModel.results.observe(this, {

        });
    }
}