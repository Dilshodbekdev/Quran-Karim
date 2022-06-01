package com.example.quranapp.domain.main

import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.juz.remote.dto.JuzResponse
import com.example.quranapp.data.main.remote.dto.*
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.juz.entity.JuzEntity
import com.example.quranapp.domain.main.entity.*
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getAllSurah(): Flow<BaseResult<List<SurahEntity>, WrappedListResponse<SurahResponse>>>

    suspend fun search(keyword: String): Flow<BaseResult<SearchEntity, WrappedResponse<SearchResponse>>>

    suspend fun pageByNumber(number: Int): Flow<BaseResult<PageEntity, WrappedResponse<PageResponse>>>

    suspend fun sajda(): Flow<BaseResult<SajdaEntity, WrappedResponse<SajdaResponse>>>
}