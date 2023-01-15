package com.example.quranapp.data.page.remote.api

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.page.remote.dto.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PageApi {

    @GET("page/{page}/{edition}")
    suspend fun getPageByNumber(
        @Path("page") page: Int,
        @Path("edition") edition: String = "uz.sodik"
    ): Response<WrappedResponse<PageResponse>>
}