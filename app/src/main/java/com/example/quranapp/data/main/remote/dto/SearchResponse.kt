package com.example.quranapp.data.main.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SearchResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("matches")
    val matches: List<Match>?
)

@Keep
data class Match(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("edition")
    val edition: Edition?,
    @SerializedName("surah")
    val surah: SurahSearch?,
    @SerializedName("numberOfAyahs")
    val numberOfAyahs: Int?,
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
    @SerializedName("type")
    val type: String?,
)

@Keep
data class SurahSearch(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("englishName")
    val englishName: String?,
    @SerializedName("englishNameTranslation")
    val englishNameTranslation: String?,
    @SerializedName("revelationType")
    val revelationType: String?,
)