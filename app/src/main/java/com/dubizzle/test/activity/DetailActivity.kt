package com.dubizzle.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dubizzle.test.common.CommonConstant
import com.dubizzle.test.data.model.Results
import com.dubizzle.test.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val results: Results by lazy {
        intent.getParcelableExtra(CommonConstant.DATA)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.result = results
    }
}