package com.dubizzle.test.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.test.adapter.DataListAdapter
import com.dubizzle.test.callback.Callback
import com.dubizzle.test.common.CommonConstant
import com.dubizzle.test.databinding.ActivityListingBinding
import com.dubizzle.test.databinding.RowListBinding
import com.dubizzle.test.domain.model.IResults
import com.dubizzle.test.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.util.Pair as sharedPair


@AndroidEntryPoint
class ListingActivity : AppCompatActivity(), Callback<IResults, RowListBinding> {

    private val binding: ActivityListingBinding by lazy {
        ActivityListingBinding.inflate(layoutInflater)
    }
    private val items = ArrayList<IResults>();
    private val adapter: DataListAdapter by lazy {
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

    override fun onItemClick(item: IResults, binding: RowListBinding) {
        val image = sharedPair.create(binding.thumbImage as View, binding.thumbImage.transitionName)
        val name = sharedPair.create(binding.name as View, binding.name.transitionName)
        val price = sharedPair.create(binding.price as View, binding.price.transitionName)
        val transitionActivityOptions: ActivityOptions =
            ActivityOptions.makeSceneTransitionAnimation(
                this,
                image,
                name, price
            )

        val intent = Intent(this, DetailActivity::class.java);
        intent.putExtra(CommonConstant.DATA, item)
        startActivity(intent, transitionActivityOptions.toBundle());
    }
}