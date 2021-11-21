package com.dubizzle.test.di

import com.dubizzle.test.data.network.FakeApiService
import com.dubizzle.test.data.network.services.ApiService
import com.dubizzle.test.data.repository.ListingRepository
import com.dubizzle.test.domain.repository.IListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkModule::class])
object FakeApiServiceModule {

    @Provides
    @Singleton
    fun providesApiServices(): ApiService {
        return FakeApiService()
    }

    @Provides
    @Singleton
    fun castFakeService(service: ApiService): FakeApiService {
        return service as FakeApiService;
    }


    @Singleton
    @Provides
    fun provideListRepository(
        service: ApiService, dispatcher: CoroutineDispatcher
    ): IListingRepository {
        return ListingRepository(service, dispatcher)
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}