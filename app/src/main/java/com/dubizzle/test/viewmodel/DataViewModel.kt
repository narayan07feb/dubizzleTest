package com.dubizzle.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubizzle.test.ExcludeFromJacocoGeneratedReport
import com.dubizzle.test.common.SingleLiveEvent
import com.dubizzle.test.data.network.countingIdlingResource
import com.dubizzle.test.domain.model.IData
import com.dubizzle.test.domain.usecase.ListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val listingUseCase: ListingUseCase) : ViewModel() {
    private var mResults = SingleLiveEvent<IData>();
    val results = mResults as LiveData<IData>
    private var mLoader = SingleLiveEvent<Boolean>();

    @ExcludeFromJacocoGeneratedReport
    val loader = mLoader as LiveData<Boolean>;

    private var mError = SingleLiveEvent<Boolean>();

    @ExcludeFromJacocoGeneratedReport
    val error = mError as LiveData<Boolean>

    fun fetchData() {
        mLoader.postValue(true)
        mError.postValue(false);
        countingIdlingResource.increment()
        listingUseCase.invoke(viewModelScope, params = Unit, onSuccess = {
            mLoader.postValue(false)
            mResults.postValue(it);
            countingIdlingResource.decrement()
        }, onFailure = {
            mLoader.postValue(false)
            mError.postValue(true);
            countingIdlingResource.decrement()
        })
    }

    @ExcludeFromJacocoGeneratedReport
    fun onRetry() {
        fetchData()
    }
}
