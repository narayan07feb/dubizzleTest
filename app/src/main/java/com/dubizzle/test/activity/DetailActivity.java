package com.dubizzle.test.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dubizzle.test.common.CommonConstant;
import com.dubizzle.test.data.model.Results;
import com.dubizzle.test.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    Results results;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        results = getIntent().getParcelableExtra(CommonConstant.DATA);
        binding.setResult(results);
    }


}
