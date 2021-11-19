package com.dubizzle.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubizzle.test.common.SingleLiveEvent
import com.dubizzle.test.domain.model.IResults
import com.dubizzle.test.domain.usecase.ListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val listingUseCase: ListingUseCase) : ViewModel() {
    init {
        fetchData()
    }

    private var mResults = SingleLiveEvent<List<IResults>>();
    val results = mResults as LiveData<List<IResults>>

    private fun fetchData() {
        listingUseCase.invoke(viewModelScope, params = Unit, onSuccess = {
            mResults.postValue(it.result);
        })
    }
}