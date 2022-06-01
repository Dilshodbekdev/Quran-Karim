package com.example.quranapp.domain.main.entity

data class SearchEntity(
    val count: Int,
    val matches: List<Match>
)

data class Match(
    val number: Int,
    val text: String,
    val edition: Edition,
    val surah: SurahSearch,
    val numberOfAyahs: Int,
)

data class Edition(
    val identifier: String,
    val language: String,
    val name: String,
    val englishName: String,
    val type: String,
)

data class SurahSearch(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val revelationType: String,
)
