package com.example.quranapp.data.main.remote.api

import com.example.quranapp.data.common.utils.WrappedListResponse
import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.main.remote.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {

    @GET("surah")
    suspend fun getAllSurah(): Response<WrappedListResponse<SurahResponse>>

    @GET("search/{keyword}/{surah}/{edition}")
    suspend fun search(
        @Path("keyword") keyword: String,
        @Path("surah") surah: String = "all",
        @Path("edition") edition: String = "en.pickthall"
    ): Response<WrappedResponse<SearchResponse>>

    @GET("page/{page}/{edition}")
    suspend fun pageByNumber(
        @Path("page") page: Int,
        @Path("edition") edition: String = "uz.sodik"
    ): Response<WrappedResponse<PageResponse>>

    @GET("sajda/{edition}")
    suspend fun sajda(
        @Path("edition") edition: String = "uz.sodik"
    ): Response<WrappedResponse<SajdaResponse>>
}