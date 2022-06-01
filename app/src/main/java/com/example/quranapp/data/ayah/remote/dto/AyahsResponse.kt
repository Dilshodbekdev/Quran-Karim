package com.example.quranapp.data.ayah.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AyahsResponse(
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
    @SerializedName("numberOfAyahs")
    val numberOfAyahs: Int?,
    @SerializedName("ayahs")
    val ayahs: List<Ayah>?,
    @SerializedName("edition")
    val edition: Edition?,
)

@Keep
data class Ayah(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("text")
    val text: String?,
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
    val hizbQuarter: Int?,
)

@Keep
data class Sajda(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("recommended")
    val recommended: Boolean?,
    @SerializedName("obligatory")
    val obligatory: Boolean?
)