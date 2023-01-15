package com.example.quranapp.domain.page

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.page.remote.dto.PageResponse
import com.example.quranapp.domain.base.BaseResult
import com.example.quranapp.domain.page.entity.PageEntity
import kotlinx.coroutines.flow.Flow

interface PageRepository {

    suspend fun getPageByNumber(page: Int): Flow<BaseResult<PageEntity, WrappedResponse<PageResponse>>>
}