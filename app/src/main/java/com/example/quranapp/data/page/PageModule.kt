package com.example.quranapp.data.page

import com.example.quranapp.data.common.module.NetworkModule
import com.example.quranapp.data.juz.remote.api.JuzApi
import com.example.quranapp.data.juz.repository.JuzRepositoryImpl
import com.example.quranapp.data.page.remote.api.PageApi
import com.example.quranapp.data.page.repository.PageRepositoryImpl
import com.example.quranapp.domain.juz.JuzRepository
import com.example.quranapp.domain.page.PageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class PageModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): PageApi = retrofit.create(PageApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: PageApi): PageRepository {
        return PageRepositoryImpl(api)
    }
}