package com.example.quranapp.data.juz.remote.api

import com.example.quranapp.data.common.utils.WrappedResponse
import com.example.quranapp.data.juz.remote.dto.JuzResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JuzApi {
    @GET("juz/{juz}/{edition}")
    suspend fun getJuzByNumber(
        @Path("juz") juz: Int,
        @Path("edition") edition: String = "uz.sodik"
    ): Response<WrappedResponse<JuzResponse>>
}