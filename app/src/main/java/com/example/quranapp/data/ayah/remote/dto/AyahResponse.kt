package com.example.quranapp.data.ayah.remote.dto

import androidx.annotation.Keep
import com.example.quranapp.data.main.remote.dto.SurahResponse
import com.google.gson.annotations.SerializedName

@Keep
data class AyahResponse(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("audio")
    val audio: String?,
    @SerializedName("audioSecondary")
    val audioSecondary: List<String>?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("edition")
    val edition: Edition?,
    @SerializedName("surah")
    val surah: SurahResponse?,
    @SerializedName("numberInSurah")
    val numberInSurah: Int?,
    @SerializedName("juz")
    val juz: Int?,
    @SerializedName("manzil")
    val manzil: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("ruku")
    val ruku: Int?,
    @SerializedName("hizbQuarter")
    val hizbQuarter: Int?
)

@Keep
data class Edition(
    @SerializedName("identifier")
    val identifier: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("englishName")
    val englishName: String?,
    @SerializedName("format")
    val format: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("direction")
    val direction: String?
)