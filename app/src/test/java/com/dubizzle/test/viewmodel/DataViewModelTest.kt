package com.dubizzle.test.viewmodel

import android.os.Looper
import com.dubizzle.test.data.network.FakeApiService
import com.dubizzle.test.domain.usecase.ListingUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class DataViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var useCase: ListingUseCase

    @Mock
    private lateinit var homeViewModel: DataViewModel

    @Inject
    lateinit var service: FakeApiService

    @Before
    fun setup() {
        hiltRule.inject()
        homeViewModel = DataViewModel(useCase)
    }

    @Test
    fun `test data success`() = runBlockingTest {
        service.failUserApi = false
        homeViewModel.fetchData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val data = homeViewModel.results.value
        data?.result?.isNotEmpty()?.let {
            assert(it)
        } ?: kotlin.run {
            assert(false)
        }
    }

    @Test
    fun `test data fail`() = runBlockingTest {
        service.failUserApi = true
        homeViewModel.fetchData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val data = homeViewModel.results.value
        data?.result?.isNullOrEmpty()?.let {
            assert(it)
        } ?: kotlin.run {
            assert(true)
        }
    }


}