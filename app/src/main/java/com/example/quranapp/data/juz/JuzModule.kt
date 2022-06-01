package com.example.quranapp.data.juz

import com.example.quranapp.data.common.module.NetworkModule
import com.example.quranapp.data.juz.remote.api.JuzApi
import com.example.quranapp.data.juz.repository.JuzRepositoryImpl
import com.example.quranapp.domain.juz.JuzRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class JuzModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): JuzApi = retrofit.create(JuzApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: JuzApi): JuzRepository {
        return JuzRepositoryImpl(api)
    }
}