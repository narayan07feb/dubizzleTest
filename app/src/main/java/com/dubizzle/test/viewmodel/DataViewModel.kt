package com.dubizzle.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubizzle.test.common.SingleLiveEvent
import com.dubizzle.test.domain.model.IData
import com.dubizzle.test.domain.usecase.ListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val listingUseCase: ListingUseCase) : ViewModel() {
    private var mResults = SingleLiveEvent<IData>();
    val results = mResults as LiveData<IData>
    private var mLoader = SingleLiveEvent<Boolean>();
    val loader = mLoader as LiveData<Boolean>;

    fun fetchData() {
        mLoader.postValue(true)
        listingUseCase.invoke(viewModelScope, params = Unit, onSuccess = {
            mLoader.postValue(false)
            mResults.postValue(it);
        })
    }
}