package com.example.quranapp.domain.main.entity

import com.google.gson.annotations.SerializedName

data class SurahEntity(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String
)