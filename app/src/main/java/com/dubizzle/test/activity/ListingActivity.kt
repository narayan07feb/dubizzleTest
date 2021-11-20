package com.dubizzle.test.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.test.adapter.DataListAdapter
import com.dubizzle.test.callback.Callback
import com.dubizzle.test.common.CommonConstant
import com.dubizzle.test.databinding.ActivityListingBinding
import com.dubizzle.test.domain.model.IResults
import com.dubizzle.test.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingActivity : AppCompatActivity(), Callback<IResults> {

    private val binding: ActivityListingBinding by lazy {
        ActivityListingBinding.inflate(layoutInflater)
    }
    val items = ArrayList<IResults>();
    val adapter: DataListAdapter by lazy {
        DataListAdapter(this, items, this@ListingActivity);
    }
    private val dataViewModel: DataViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            lifecycleOwner = this@ListingActivity
            viewModel = dataViewModel
            recyclerview.layoutManager = LinearLayoutManager(this@ListingActivity)
            recyclerview.adapter = adapter;
        }

        dataViewModel.results.observe(this, {
            updatedata(it)
        });
        dataViewModel.fetchData()


    }

    private fun updatedata(list: List<IResults>) {
        items.clear()
        items.addAll(list);
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(item: IResults) {
        val intent = Intent(this, DetailActivity::class.java);
        intent.putExtra(CommonConstant.DATA, item)
        startActivity(intent);
    }
}