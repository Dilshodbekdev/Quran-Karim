package com.example.quranapp.domain.juz

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.juz.remote.dto.JuzResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.juz.entity.JuzEntity
import kotlinx.coroutines.flow.Flow

interface JuzRepository {

    suspend fun getJuzByNumber(juz: Int): Flow<BaseResult<JuzEntity, WrappedResponse<JuzResponse>>>
}