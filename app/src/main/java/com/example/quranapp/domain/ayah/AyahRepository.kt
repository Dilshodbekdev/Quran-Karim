package com.example.quranapp.domain.ayah

import com.example.quranapp.data.ayah.remote.dto.AyahResponse
import com.example.quranapp.data.ayah.remote.dto.AyahsResponse
import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.domain.ayah.entity.AyahEntity
import com.example.quranapp.domain.ayah.entity.AyahsEntity
import com.example.quranapp.domain.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface AyahRepository {

    suspend fun ayah(reference: Int): Flow<BaseResult<AyahEntity, WrappedResponse<AyahResponse>>>

    suspend fun ayahs(surah: Int): Flow<BaseResult<AyahsEntity, WrappedResponse<AyahsResponse>>>
}