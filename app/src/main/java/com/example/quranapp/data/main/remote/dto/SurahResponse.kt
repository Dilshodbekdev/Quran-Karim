package com.example.quranapp.data.main.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SurahResponse(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("englishName")
    val englishName: String?,
    @SerializedName("englishNameTranslation")
    val englishNameTranslation: String?,
    @SerializedName("numberOfAyahs")
    val numberOfAyahs: Int?,
    @SerializedName("revelationType")
    val revelationType: String?,
)
