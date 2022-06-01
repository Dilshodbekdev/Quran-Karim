package com.example.quranapp.data.ayah.remote.api

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.ayah.remote.dto.AyahResponse
import com.example.quranapp.data.ayah.remote.dto.AyahsResponse
import com.example.quranapp.data.common.utils.WrappedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AyahApi {

    @GET("ayah/{reference}/{edition}")
    suspend fun getAyah(
        @Path("reference") reference: Int,
        @Path("edition") edition: String = "uz.sodik"
    ): Response<WrappedResponse<AyahResponse>>

    @GET("surah/{surah}/{edition}")
    suspend fun getAyahsBySurah(
        @Path("surah") surah: Int,
        @Path("edition") edition: String = "uz.sodik"
    ): Response<WrappedResponse<AyahsResponse>>
}