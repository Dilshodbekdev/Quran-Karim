package com.example.quranapp.domain.juz.entity

import com.example.quranapp.domain.main.entity.SurahEntity


data class JuzEntity(
    val number: Int,
    val ayahs: List<Ayah>
)

data class Ayah(
    val number: Int,
    val text: String,
    val surah: SurahEntity,
    val numberInSurah: Int,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val hizbQuarter: Int
)