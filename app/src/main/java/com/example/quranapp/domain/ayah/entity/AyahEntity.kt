package com.example.quranapp.domain.ayah.entity

import com.example.quranapp.domain.main.entity.SurahEntity

data class AyahEntity(
    val number: Int,
    val audio: String,
    val audioSecondary: List<String>,
    val text: String,
    val edition: EditionEntity,
    val surah: SurahEntity,
    val numberInSurah: Int,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val hizbQuarter: Int
)

data class EditionEntity(
    val identifier: String,
    val language: String,
    val name: String,
    val englishName: String,
    val format: String,
    val type: String,
    val direction: String
)