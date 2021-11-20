package com.dubizzle.test.di

import com.dubizzle.test.data.network.ApiConstant
import com.dubizzle.test.data.network.services.ApiService
import com.dubizzle.test.data.repository.ListingRepository
import com.dubizzle.test.domain.repository.IListingRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {

        val level = HttpLoggingInterceptor();
        level.level = HttpLoggingInterceptor.Level.BASIC
        val okHttp = OkHttpClient.Builder().addInterceptor(level).build()
        return Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .client(okHttp)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
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
        return Dispatchers.IO
    }
}