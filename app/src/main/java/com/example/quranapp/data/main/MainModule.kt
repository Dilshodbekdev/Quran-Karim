package com.example.quranapp.data.main

import com.example.quranapp.data.common.module.NetworkModule
import com.example.quranapp.data.main.remote.api.MainApi
import com.example.quranapp.data.main.repository.MainRepositoryImpl
import com.example.quranapp.domain.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: MainApi): MainRepository {
        return MainRepositoryImpl(api)
    }
}