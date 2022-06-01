package com.example.quranapp.data.ayah

import com.example.quranapp.data.ayah.remote.api.AyahApi
import com.example.quranapp.data.ayah.repository.AyahRepositoryImpl
import com.example.quranapp.data.common.module.NetworkModule
import com.example.quranapp.data.main.remote.api.MainApi
import com.example.quranapp.data.main.repository.MainRepositoryImpl
import com.example.quranapp.domain.ayah.AyahRepository
import com.example.quranapp.domain.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SurahModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): AyahApi = retrofit.create(AyahApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: AyahApi): AyahRepository {
        return AyahRepositoryImpl(api)
    }
}